package com.bookmark.stock.domain.stock;

import com.bookmark.stock.domain.stock.entity.StockEntity;

import java.util.List;
import java.util.Optional;

public interface StockCacheRepository {
    Optional<StockEntity> findByTicker(String ticker);

    void save(StockEntity stock);

    void saveAll(List<StockEntity> stockList);
}
