package com.bookmark.stock.domain.stock;

import com.bookmark.stock.common.exceptions.BaseException;
import com.bookmark.stock.domain.stock.StockEnum;
import com.bookmark.stock.domain.stock.StockException;
import com.bookmark.stock.domain.stock.entity.StockEntity;

public record StockDomainDto() {

    //
    public record StockSearchDto(
            Long stockId,
            String ticker,
            String stockName
    ){
        public StockSearchDto{
            if(stockId == null && ticker == null && stockName == null){
                throw new BaseException(StockException.SearchAllAttributeNullException);
            }
        }

        public static StockSearchDto fromDomain(StockEntity stockEntity){
            return new StockSearchDto(stockEntity.getId(),stockEntity.getTicker(), stockEntity.getStockName());
        }
    }


}
