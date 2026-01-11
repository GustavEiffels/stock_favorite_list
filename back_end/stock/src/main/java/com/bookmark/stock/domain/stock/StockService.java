package com.bookmark.stock.domain.stock;

import com.bookmark.stock.domain.stock.dto.StockApiDto;
import com.bookmark.stock.domain.stock.dto.StockCacheDto;
import com.bookmark.stock.domain.stock.dto.StockDomainDto;
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
    private final StockApiClient stockApiClient;
    private final StockCacheRepository cacheRepository;

    public StockEntity createStock(StockDomainDto.StockCreateDto createDto){
        return repository.save(createDto.toDomain());
    }


    @Transactional
    public List<StockDomainDto.StockSearchDto> findStockByTicker(StockDomainDto.StockSearchDto searchDto){
        // ticker 로 조회
        if(!searchDto.ticker().isEmpty()){
            String ticker = searchDto.ticker();
            // 1. cache 에서 ticker 로 조회
            Optional<StockCacheDto.StockInfoCache> stockInfoCache =
                    cacheRepository.findByTicker(ticker);

            // ticker 조회 시 존재
            if(stockInfoCache.isPresent()){
                assert false;
                log.info("{} : CACHE 에 존재!",ticker);
                return List.of(stockInfoCache.get().toSearchDto());
            }
            // 존재 하지 않으면 DB 조회해서 데이터 넣음
            else{
                // 2. DB 조회
                Optional<StockEntity> findStock = repository.findStock(searchDto);
                log.info("DB 조회 : {}",findStock);

                // DB 조회 시 존재하지 않으면 API 호출
                if(findStock.isEmpty()){
                    StockApiDto.PinnhubSymbolResponse response = stockApiClient.findSymbol(searchDto.ticker());

                    // 3. API 호출 시 존재
                    if(!response.result().isEmpty()){
                        log.info("{} : API 조회 시 존재!",ticker);
                        List<StockEntity> newStockInfoList = response.result().stream()
                                .map(StockApiDto.PinnhubSymbolProfile::toDomain).toList();
                        repository.saveAll(newStockInfoList);
                        cacheRepository.saveAll(newStockInfoList.stream().map(StockCacheDto.StockInfoCache::fromDomain).toList());
                        return newStockInfoList.stream().map(StockDomainDto.StockSearchDto::fromDomain).toList();
                    }
                    else{
                        log.info("{} : API 조회 시 존재하지 않음!",ticker);
                        throw new RuntimeException("NOT FOUND FROM THAT TICKER");
                    }
                }
                else{
                    log.info("{} : DB 에 존재!",ticker);
                    cacheRepository.save(StockCacheDto.StockInfoCache.fromDomain(findStock.get()));
                    return List.of(StockDomainDto.StockSearchDto.fromDomain(findStock.get()));
                }
            }
        }
        else{
            throw new RuntimeException("TICKER 가 없습니다.");
        }
    }

}
