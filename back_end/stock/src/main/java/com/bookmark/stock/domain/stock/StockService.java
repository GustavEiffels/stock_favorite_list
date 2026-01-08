package com.bookmark.stock.domain.stock;

import com.bookmark.stock.domain.stock.dto.StockApiDto;
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
    public final StockApiClient stockApiClient;

    public StockEntity createStock(StockDomainDto.StockCreateDto createDto){
        return repository.save(createDto.toDomain());
    }

    @Transactional
    public void findByTickerStock(StockDomainDto.StockSearchDto searchDto){
        if(!searchDto.ticker().isEmpty()){
            Optional<StockEntity> findStock = repository.findStock(searchDto);
            if(findStock.isEmpty()){
                StockApiDto.PinnhubSymbolResponse response = stockApiClient.findSymbol(searchDto.ticker());
                if(!response.result().isEmpty()){
                    List<StockEntity> newStockInfoList = response.result().stream()
                            .map(StockApiDto.PinnhubSymbolProfile::toDomain).toList();
                    log.info("save stock list : {}",newStockInfoList);
                    repository.saveAll(newStockInfoList);
                }
            }
        }
    }

}
