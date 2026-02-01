package com.bookmark.stock.interfaces.api.stock;

import com.bookmark.stock.domain.stock.StockDomainDto;

public record StockApiDto() {

    public record SearchRequest(
            String ticker,
            String stockName
    ){
        public StockDomainDto.StockSearchDto toDomain(){
            return new StockDomainDto.StockSearchDto(
                    null,
                    ticker,
                    stockName
            );
        }
    }


}
