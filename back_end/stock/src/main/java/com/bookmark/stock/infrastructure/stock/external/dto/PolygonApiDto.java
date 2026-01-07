package com.bookmark.stock.infrastructure.stock.external.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PolygonApiDto() {

    public record PolygonTickerDto(
            String ticker,
            String name,
            String market,
            @JsonProperty("primary_exchange")
            String primaryExchange,
            String type,
            Boolean active
    ){
        public ExternalStockDto toExternalStockDto(){
            return new ExternalStockDto(
                    ticker,
                    name,
                    primaryExchange
            );
        }
    }

    public record PolygonTickerResponse(
            List<PolygonTickerDto> results,
            String status,
            Integer count
    ){}

    public record ExternalStockDto(
            String ticker,
            String name,
            String exchange
    ){

    }
}
