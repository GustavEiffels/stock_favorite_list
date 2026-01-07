package com.bookmark.stock.domain.stock.entity;

import com.bookmark.stock.common.entity.BaseEntity;
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

    public static StockEntity create(String ticker,String stockName){
        return create(ticker,stockName, null);
    }

    public static StockEntity create(String ticker,String stockName,String stockNameKr){
        return new StockEntity(
                null,
                ticker,
                stockName,
                stockNameKr
        );
    }
}
