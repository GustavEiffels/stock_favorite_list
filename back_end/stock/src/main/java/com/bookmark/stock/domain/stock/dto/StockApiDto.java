package com.bookmark.stock.domain.stock.dto;

import com.bookmark.stock.domain.stock.StockEnum;
import com.bookmark.stock.domain.stock.entity.StockEntity;

import java.util.List;

public record StockApiDto() {

    public record PinnhubProfileResponse(
            String ticker,
            String name,
            String country,
            String currency,
            String exchange,
            String logo,
            Double marketCapitalization,
            String finnhubIndustry
    ){}

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
        public StockEntity toDomain(){
            return StockEntity.create(
                    symbol,
                    description,
                    type.equals(StockEnum.Type.ETF.name())?
                            StockEnum.Type.ETF:
                            StockEnum.Type.STOCK
            );
        }
    }

}
