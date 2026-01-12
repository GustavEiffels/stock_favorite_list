package com.bookmark.stock.infrastructure.stock.external;

import com.bookmark.stock.domain.stock.StockEnum;
import com.bookmark.stock.domain.stock.entity.StockEntity;

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
        public StockEntity toDomain(){
            return StockEntity.create(
                    symbol,
                    description,
                    type.equals(StockEnum.Type.STOCK.name())?
                            StockEnum.Type.STOCK:
                            StockEnum.Type.ETF
            );
        }
    }

}
