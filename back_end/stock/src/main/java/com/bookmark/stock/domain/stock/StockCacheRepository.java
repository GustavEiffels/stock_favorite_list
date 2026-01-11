package com.bookmark.stock.domain.stock;

import com.bookmark.stock.domain.stock.dto.StockCacheDto;

import java.util.List;
import java.util.Optional;

public interface StockCacheRepository {
    Optional<StockCacheDto.StockInfoCache> findByTicker(String ticker);

    void save(StockCacheDto.StockInfoCache stockInfoCache);

    void saveAll(List<StockCacheDto.StockInfoCache> stockInfoCacheList);
}
