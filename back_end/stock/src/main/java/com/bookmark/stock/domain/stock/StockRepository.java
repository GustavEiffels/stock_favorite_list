package com.bookmark.stock.domain.stock;

import com.bookmark.stock.domain.stock.entity.StockEntity;

import java.util.List;
import java.util.Optional;

public interface StockRepository {

    StockEntity save(StockEntity stockEntity);
    List<StockEntity> saveAll(List<StockEntity> stockEntityList);

    Optional<StockEntity> findStock(StockDomainDto.StockSearchDto stockSearchDto);
}
