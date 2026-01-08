package com.bookmark.stock.domain.stock.entity;

import com.bookmark.stock.common.entity.BaseEntity;
import com.bookmark.stock.domain.stock.StockEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Stock")
public class StockEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ticker;
    private String stockName; // USA
    private String stockNameKr;

    @Enumerated(EnumType.STRING)
    private StockEnum.Type type;

    public static StockEntity create(String ticker,String stockName, StockEnum.Type type){
        return create(ticker,stockName,null,type);
    }

    public static StockEntity create(String ticker,String stockName,String stockNameKr,StockEnum.Type type){
        return new StockEntity(
                null,
                ticker,
                stockName,
                stockNameKr,
                type
        );
    }
}
