package com.bookmark.stock.domain.stock;

import com.bookmark.stock.common.exceptions.BaseException;
import com.bookmark.stock.domain.stock.entity.StockEntity;

public record StockDomainDto() {

    //
    public record StockSearchDto(
            Long stockId,
            String ticker,
            String stockName,
            String stockNameKr
    ){
        public StockSearchDto{
            if(stockId == null && ticker == null && stockName == null && stockNameKr == null){
                throw new BaseException(StockException.SearchAllAttributeNullException);
            }
        }
    }

    //
    public record StockCreateDto(
            String ticker,
            String stockName,
            String stockNameKr
    ){

        public StockEntity toDomain(){
            return stockNameKr==null ?
                    StockEntity.create(ticker,stockName):StockEntity.create(ticker,stockName,stockNameKr);
        }
    }
}
