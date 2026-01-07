package com.bookmark.stock.infrastructure.stock.external.client;


import com.bookmark.stock.infrastructure.stock.external.dto.PolygonApiDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class PolygonApiClient implements StockApiClient{
    private final WebClient polygonWebClient;

    @Value("${polygon.api.key}")
    private String apiKey;

    @Override
    public List<PolygonApiDto.ExternalStockDto> fetchTopStocksByMarketCap(int limit) {
        PolygonApiDto.PolygonTickerResponse response =  polygonWebClient.get()
                .uri(uriBuilder -> uriBuilder.path("/v3/reference/tickers")
                        .queryParam("market", "stocks")
                        .queryParam("exchange", "XNYS")
                        .queryParam("active", "true")
                        .queryParam("limit", limit)
                        .queryParam("apikey", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(PolygonApiDto.PolygonTickerResponse.class)
                .block();

        assert response != null;
        return response.results().stream().
                filter(dto->"CS".equals(dto.type()))
                .map(PolygonApiDto.PolygonTickerDto::toExternalStockDto)
                .toList();
    }
}
