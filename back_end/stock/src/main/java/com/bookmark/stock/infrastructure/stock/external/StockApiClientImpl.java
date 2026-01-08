package com.bookmark.stock.infrastructure.stock.external;

import com.bookmark.stock.domain.stock.StockApiClient;
import com.bookmark.stock.domain.stock.dto.StockApiDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockApiClientImpl implements StockApiClient {

    private final FinnhubClient finnhubClient;

    @Override
    public StockApiDto.PinnhubSymbolResponse findSymbol(String symbol) {
        return finnhubClient.getSearchBySymbol(symbol);
    }
}
