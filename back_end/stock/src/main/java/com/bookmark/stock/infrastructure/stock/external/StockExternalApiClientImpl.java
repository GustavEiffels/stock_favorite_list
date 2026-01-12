package com.bookmark.stock.infrastructure.stock.external;

import com.bookmark.stock.domain.stock.StockExternalApiClient;
import com.bookmark.stock.domain.stock.entity.StockEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StockExternalApiClientImpl implements StockExternalApiClient {

    private final FinnhubClient finnhubClient;

    @Override
    public List<StockEntity> findSymbol(String symbol) {
        StockExternalApiDto.PinnhubSymbolResponse response = finnhubClient.getSearchBySymbol(symbol);

        return  finnhubClient.getSearchBySymbol(symbol).result().stream()
                .map(StockExternalApiDto.PinnhubSymbolProfile::toDomain)
                .toList();

    }
}
