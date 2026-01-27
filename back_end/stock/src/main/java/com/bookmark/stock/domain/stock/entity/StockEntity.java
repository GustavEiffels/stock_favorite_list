package com.bookmark.stock.domain.stock.entity;

import com.bookmark.stock.common.entity.BaseEntity;
import com.bookmark.stock.domain.stock.StockEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "Stock",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_stock_ticker",
                        columnNames = {"ticker"}
                )
        }
)
@Builder
public class StockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ticker;
    private String stockName; // USA
    private String currency;
}
