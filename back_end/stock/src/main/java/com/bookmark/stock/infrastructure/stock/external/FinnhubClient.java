package com.bookmark.stock.infrastructure.stock.external;

import com.bookmark.stock.domain.stock.dto.StockApiDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
@Slf4j
public class FinnhubClient {
    private final WebClient webClient;
    public final String apiKey;
    public FinnhubClient(
            @Qualifier("finnhubWebClient") WebClient webClient,
            @Value("${finnhub.api.key}") String apiKey) {
        this.webClient = webClient;
        this.apiKey = apiKey;
    }

    public StockApiDto.PinnhubProfileResponse getStockProfile(String ticker){
        log.info("ticker : {}",ticker);
        try {
            StockApiDto.PinnhubProfileResponse response = webClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/stock/profile2")
                            .queryParam("symbol",ticker.toUpperCase())
                            .queryParam("token",apiKey)
                            .build())
                    .retrieve()
                    .bodyToMono(StockApiDto.PinnhubProfileResponse.class)
                    .block();
            log.info("call out STOCK success : {}",response);
            return response;
        }
        catch (Exception e){
            log.error(e.getLocalizedMessage());
            return null;
        }
    }

    public StockApiDto.PinnhubSymbolResponse getSearchBySymbol(String symbol){
        log.info("symbol : {}",symbol);
        try {
            StockApiDto.PinnhubSymbolResponse response = webClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/search")
                            .queryParam("q",symbol.toUpperCase())
                            .queryParam("token",apiKey)
                            .build())
                    .retrieve()
                    .bodyToMono(StockApiDto.PinnhubSymbolResponse.class)
                    .block();
            log.info("call out symbol success : {}",response);
            return response;
        }
        catch (Exception e){
            log.error(e.getLocalizedMessage());
            return null;
        }
    }
}
