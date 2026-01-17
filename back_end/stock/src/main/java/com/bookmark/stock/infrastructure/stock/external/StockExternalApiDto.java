package com.bookmark.stock.infrastructure.stock.external;

import com.bookmark.stock.domain.stock.StockEnum;
import com.bookmark.stock.domain.stock.entity.StockEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public record StockExternalApiDto() {

    public record PinnhubSymbolResponse(
            Integer count,
            List<PinnhubSymbolProfile> result
    ){}

    public record PinnhubSymbolProfile(
            String description,
            String displaySymbol,
            String symbol,
            String type
    ){
    }

    public record YahooFinanceResponse(
            YahooChart chart
    ){}

    public record YahooChart(
            List<YahooResult> result,
            String error
    ){}

    public record YahooResult(
            YahooMeta meta
    ){}

    public record YahooMeta(
            String symbol,
            @JsonProperty("regularMarketPrice")
            Double regularMarketPrice,
            @JsonProperty("chartPreviousClose")
            Double chartPreviousClose,
            @JsonProperty("longName")
            String longName,
            @JsonProperty("shortName")
            String shortName,
            String currency,
            @JsonProperty("exchangeName")
            String exchangeName,
            @JsonProperty("quoteType")
            String quoteType,
            @JsonProperty("regularMarketVolume")
            Long regularMarketVolume
    ){
        public StockEntity toDomain(){
            String stockName = longName != null ? longName : shortName;

            return StockEntity.builder()
                    .ticker(symbol)
                    .stockName(stockName)
                    .currency(currency)
                    .build();
        }
    }
}