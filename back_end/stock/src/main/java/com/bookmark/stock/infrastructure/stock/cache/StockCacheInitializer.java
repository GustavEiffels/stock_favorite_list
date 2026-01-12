package com.bookmark.stock.infrastructure.stock.cache;

import com.bookmark.stock.domain.stock.StockCacheRepository;
import com.bookmark.stock.domain.stock.StockRepository;
import com.bookmark.stock.domain.stock.entity.StockEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
@Order(1)
public class StockCacheInitializer implements ApplicationRunner {

    private final StockRepository stockRepository;
    private final StockCacheRepository cacheRepository;


    @Value("${stock.cache.init.enabled:true}")
    private boolean enabled;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (!enabled) return;
        initializeCacheAsync();
    }

    @Async
    public void initializeCacheAsync() {
        log.info("Initializing stock cache...");
        List<StockEntity> stocks = stockRepository.find100Stock();
        cacheRepository.saveAll(stocks);
        log.info("Cache initialized with {} stocks", stocks.size());
    }
}
