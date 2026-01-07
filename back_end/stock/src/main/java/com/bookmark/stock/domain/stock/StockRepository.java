package com.bookmark.stock.domain.stock;

import com.bookmark.stock.domain.stock.entity.StockEntity;

import java.util.Optional;

public interface StockRepository {

    StockEntity save(StockEntity stockEntity);

    Optional<StockEntity> findStock(StockDomainDto.StockSearchDto stockSearchDto);
}
