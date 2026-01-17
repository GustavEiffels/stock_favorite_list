package com.bookmark.stock.infrastructure.stock.external;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Slf4j
public class YahooFinanceClient {

    private final WebClient webClient;

    public YahooFinanceClient(@Qualifier("yahooFinanceWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public StockExternalApiDto.YahooFinanceResponse getStockQuote(String ticker) {
        log.info("Fetching stock from Yahoo Finance - ticker: {}", ticker);

        try {
            StockExternalApiDto.YahooFinanceResponse response = webClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/v8/finance/chart/{symbol}")
                            .build(ticker.toUpperCase()))
                    .retrieve()
                    .bodyToMono(StockExternalApiDto.YahooFinanceResponse.class)
                    .block();

            log.info("Yahoo Finance API call success - ticker: {}", ticker);
            log.info("Yahoo Finance response - ticker: {}", response);
            return response;

        } catch (Exception e) {
            log.error("Yahoo Finance API call failed - ticker: {}, error: {}",
                    ticker, e.getMessage());
            return null;
        }
    }

}
