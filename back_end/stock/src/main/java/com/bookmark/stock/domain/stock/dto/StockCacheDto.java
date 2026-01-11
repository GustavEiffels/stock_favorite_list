package com.bookmark.stock.domain.stock.dto;


import com.bookmark.stock.domain.stock.entity.StockEntity;

public record StockCacheDto() {

    public record StockInfoCache(
            String ticker,
            String stockName,
            Long stockId
    ){
        public static StockInfoCache of(String ticker, String stockName, Long stockId){
            return new StockInfoCache(ticker,stockName,stockId);
        }

        public static StockInfoCache fromDomain(StockEntity stock){
            return new StockInfoCache(stock.getTicker(),stock.getStockName(),stock.getId());
        }

        public StockDomainDto.StockSearchDto toSearchDto(){
            return new StockDomainDto.StockSearchDto(
                    stockId,
                    ticker,
                    stockName,
                    null
            );
        }
    }
}
