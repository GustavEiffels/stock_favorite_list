package com.bookmark.stock.infrastructure.stock.cache;


import com.bookmark.stock.domain.stock.StockEnum;
import com.bookmark.stock.domain.stock.entity.StockEntity;

public record StockCacheDto() {

    public record StockInfoCache(
            Long stockId,
            String ticker,
            String stockName,
            String currency
    ){

        public static StockInfoCache fromDomain(StockEntity stock){
            return new StockInfoCache(
                    stock.getId(),
                    stock.getTicker(),
                    stock.getStockName(),
                    stock.getCurrency()
            );
        }
        public StockEntity toDomain(){
            return StockEntity.builder()
                    .id(stockId)
                    .ticker(ticker)
                    .stockName(stockName)
                    .currency(currency)
                    .build();
        }
    }
}
