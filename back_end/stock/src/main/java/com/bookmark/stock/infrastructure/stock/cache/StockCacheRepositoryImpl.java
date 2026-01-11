package com.bookmark.stock.infrastructure.stock.cache;

import com.bookmark.stock.domain.stock.StockCacheRepository;
import com.bookmark.stock.domain.stock.dto.StockCacheDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class StockCacheRepositoryImpl implements StockCacheRepository {
    private final CacheManager cacheManager;

    private static final String CACHE_BY_TICKER = "stockInfoByTicker";
    private static final String CACHE_BY_NAME = "stockInfoByName";

    @Override
    public Optional<StockCacheDto.StockInfoCache> findByTicker(String ticker) {
        Cache cache = cacheManager.getCache(CACHE_BY_TICKER);
        if (cache != null) {
            Cache.ValueWrapper wrapper = cache.get(ticker);
            if (wrapper != null) {
                log.debug("Cache hit - ticker: {}", ticker);
                return Optional.of((StockCacheDto.StockInfoCache) wrapper.get());
            }
        }
        log.debug("Cache miss - ticker: {}", ticker);
        return Optional.empty();
    }

    @Override
    public void save(StockCacheDto.StockInfoCache stockInfoCache) {
        log.info("{} : cache 에 단일 저장",stockInfoCache);
        Cache tickerCache = cacheManager.getCache(CACHE_BY_TICKER);
        Cache nameCache = cacheManager.getCache(CACHE_BY_NAME);
        tickerCache.put(stockInfoCache.ticker(), stockInfoCache);
        nameCache.put(stockInfoCache.stockName(), stockInfoCache);
    }

    @Override
    public void saveAll(List<StockCacheDto.StockInfoCache> stockInfoCacheList) {
        log.info("{} : cache 에 리스트 저장",stockInfoCacheList);
        Cache tickerCache = cacheManager.getCache(CACHE_BY_TICKER);
        Cache nameCache = cacheManager.getCache(CACHE_BY_NAME);

            stockInfoCacheList.forEach(stockInfo -> {
                tickerCache.put(stockInfo.ticker(), stockInfo);
                nameCache.put(stockInfo.stockName(), stockInfo);
            });
    }


}
