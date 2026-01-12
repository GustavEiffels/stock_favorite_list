package com.bookmark.stock.infrastructure.stock.external;

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


    public StockExternalApiDto.PinnhubSymbolResponse getSearchBySymbol(String symbol){
        log.info("symbol : {}",symbol);
        try {
            StockExternalApiDto.PinnhubSymbolResponse response = webClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/search")
                            .queryParam("q",symbol.toUpperCase())
                            .queryParam("token",apiKey)
                            .build())
                    .retrieve()
                    .bodyToMono(StockExternalApiDto.PinnhubSymbolResponse.class)
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
