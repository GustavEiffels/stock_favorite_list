package com.bookmark.stock.infrastructure.stock.external;

import com.bookmark.stock.domain.stock.StockExternalApiClient;
import com.bookmark.stock.domain.stock.entity.StockEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StockExternalApiClientImpl implements StockExternalApiClient {

    private final YahooFinanceClient yahooFinanceClient;

    @Override
    public List<StockEntity> findSymbol(String symbol) {
        return yahooFinanceClient.getStockQuote(symbol)
                .chart().result().stream().map(StockExternalApiDto.YahooResult::meta)
                .map(StockExternalApiDto.YahooMeta::toDomain)
                .toList();
    }
}
