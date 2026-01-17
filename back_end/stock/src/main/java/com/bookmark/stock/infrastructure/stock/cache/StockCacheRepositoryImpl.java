package com.bookmark.stock.infrastructure.stock.cache;

import com.bookmark.stock.domain.stock.StockCacheRepository;
import com.bookmark.stock.domain.stock.entity.StockEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Repository;

import java.util.Collections;
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
    public Optional<StockEntity> findByTicker(String ticker) {
        Cache cache = cacheManager.getCache(CACHE_BY_TICKER);
        if (cache != null) {
            Cache.ValueWrapper wrapper = cache.get(ticker);
            if (wrapper != null) {
                log.debug("Cache hit - ticker: {}", ticker);
                StockCacheDto.StockInfoCache stockInfoCache = ((StockCacheDto.StockInfoCache) wrapper.get());
                return Optional.of(stockInfoCache.toDomain());
            }
        }
        log.debug("Cache miss - ticker: {}", ticker);
        return Optional.empty();
    }

    @Override
    public List<StockEntity> findAllInCache() {
        Cache cache = cacheManager.getCache(CACHE_BY_TICKER);
        if(cache == null){
            return Collections.emptyList();
        }

        Object nativeCache = cache.getNativeCache();

        if(nativeCache instanceof com.github.benmanes.caffeine.cache.Cache){
            com.github.benmanes.caffeine.cache.Cache<Object,Object> caffeineCache =
                    (com.github.benmanes.caffeine.cache.Cache<Object,Object>) nativeCache;

            return caffeineCache.asMap().values().stream()
                    .map(obj -> (StockCacheDto.StockInfoCache) obj)
                    .map(StockCacheDto.StockInfoCache::toDomain)
                    .toList();
        }
        return Collections.emptyList();
    }

    @Override
    public void save(StockEntity stockEntity) {
        StockCacheDto.StockInfoCache stockInfoCache = StockCacheDto.StockInfoCache.fromDomain(stockEntity);
        Cache tickerCache = cacheManager.getCache(CACHE_BY_TICKER);
        Cache nameCache = cacheManager.getCache(CACHE_BY_NAME);
        tickerCache.put(stockInfoCache.ticker(), stockInfoCache);
        nameCache.put(stockInfoCache.stockName(), stockInfoCache);
    }

    @Override
    public void saveAll(List<StockEntity> stockList) {
        Cache tickerCache = cacheManager.getCache(CACHE_BY_TICKER);
        Cache nameCache = cacheManager.getCache(CACHE_BY_NAME);
        stockList.forEach(stock -> {
                StockCacheDto.StockInfoCache stockInfoCache = StockCacheDto.StockInfoCache.fromDomain(stock);
                tickerCache.put(stockInfoCache.ticker(), stockInfoCache);
                nameCache.put(stockInfoCache.stockName(), stockInfoCache);
            });
    }



}
