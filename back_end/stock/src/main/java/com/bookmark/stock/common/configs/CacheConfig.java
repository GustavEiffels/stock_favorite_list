package com.bookmark.stock.common.configs;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager CaffeineCacheManager(){
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        simpleCacheManager.setCaches(Arrays.asList(
                buildCache("stockInfoByTicker",10000,24,TimeUnit.DAYS),
                buildCache("allStocks",10000,24,TimeUnit.DAYS),
                buildCache("stockInfoByName",1,24,TimeUnit.DAYS)
        ));
        return  simpleCacheManager;
    }

    private CaffeineCache buildCache(String name, int maximumSize, long duration, TimeUnit timeUnit) {
        return new CaffeineCache(name, Caffeine.newBuilder()
                .maximumSize(maximumSize)
                .expireAfterWrite(duration, timeUnit)
                .recordStats()
                .build());
    }
}
