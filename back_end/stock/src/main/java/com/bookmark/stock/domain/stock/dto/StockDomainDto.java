package com.bookmark.stock.domain.stock.dto;

import com.bookmark.stock.common.exceptions.BaseException;
import com.bookmark.stock.domain.stock.StockEnum;
import com.bookmark.stock.domain.stock.StockException;
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



        public static StockSearchDto fromDomain(StockEntity stockEntity){
            return new StockSearchDto(stockEntity.getId(),stockEntity.getTicker(), stockEntity.getStockName(), stockEntity.getStockNameKr());
        }
    }

    //
    public record StockCreateDto(
            String ticker,
            String stockName,
            String stockNameKr,
            StockEnum.Type type
    ){
        public StockEntity toDomain(){
            return stockNameKr==null ?
                    StockEntity.create(ticker,stockName,type):StockEntity.create(ticker,stockName,stockNameKr,type);
        }
    }

}
