package com.bookmark.stock.infrastructure.stock.cache;


import com.bookmark.stock.domain.stock.StockEnum;
import com.bookmark.stock.domain.stock.entity.StockEntity;

public record StockCacheDto() {

    public record StockInfoCache(
            String ticker,
            String stockName,
            Long stockId,
            StockEnum.Type type
    ){

        public static StockInfoCache fromDomain(StockEntity stock){
            return new StockInfoCache(stock.getTicker(),stock.getStockName(),stock.getId(),stock.getType());
        }
        public StockEntity toDomain(){
            return new StockEntity(stockId,ticker,stockName,null,type);
        }
    }
}
