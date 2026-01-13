package com.bookmark.stock.domain.stock;

import com.bookmark.stock.domain.stock.entity.StockEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockService {
    private final StockRepository repository;
    private final StockExternalApiClient externalRepository;
    private final StockCacheRepository cacheRepository;

    // INSERT
    public StockEntity createStock(StockDomainDto.StockCreateDto createDto){
        return repository.save(createDto.toDomain());
    }

    // Business
    @Transactional
    public List<StockDomainDto.StockSearchDto> findStockByTicker(StockDomainDto.StockSearchDto searchDto){
        // ticker 로 조회
        if(searchDto.ticker().isEmpty()){
            throw new RuntimeException("TICKER 가 없습니다.");
        }
        String ticker = searchDto.ticker();

        // 1. cache 에 조회
        Optional<StockEntity> stockInfoFromCache = cacheRepository.findByTicker(ticker);
        // 존재하면 return
        if(stockInfoFromCache.isPresent()){
            log.info("Cache 에 존재 : {}",ticker);
            return List.of(StockDomainDto.StockSearchDto.fromDomain(stockInfoFromCache.get()));
        }

        // 2. cache 에 존재하지 않아 DB 에 조회
        Optional<StockEntity> stockInfoFromRepo = repository.findStock(searchDto);
        // 존재하면 cache 에 저장하고 값 return
        if(stockInfoFromRepo.isPresent()){
            log.info("DB 에 존재 : {}",ticker);
            StockEntity stock = stockInfoFromRepo.get();
            cacheRepository.save(stock);
            return List.of(StockDomainDto.StockSearchDto.fromDomain(stock));
        }

        // 3. DB 에도 존재하지 않아 API 호출
        List<StockEntity> stockInfoListFromExternal = externalRepository.findSymbol(ticker);
        // API 에도 존재하지 않으면 사용자 에러로 간주
        if(stockInfoListFromExternal.isEmpty()){
            log.info("API 에도 존재 하지 않음: {}",ticker);
           throw new RuntimeException("존재하지 않은 TICKER 입니다.");
        }
        // API 에서 조회 확인 시, DB 에 저장 Cache 에 Load
        log.info("API 에서 존재 확인 : {}",ticker);
        repository.saveAll(stockInfoListFromExternal);
        cacheRepository.saveAll(stockInfoListFromExternal);
        return stockInfoListFromExternal.stream().map(StockDomainDto.StockSearchDto::fromDomain).toList();
    }




}
