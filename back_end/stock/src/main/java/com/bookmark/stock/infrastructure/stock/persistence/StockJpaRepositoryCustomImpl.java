package com.bookmark.stock.infrastructure.stock.persistence;

import com.bookmark.stock.domain.stock.StockDomainDto;
import com.bookmark.stock.domain.stock.entity.StockEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.bookmark.stock.domain.stock.entity.QStockEntity.*;

@Repository
@RequiredArgsConstructor
public class StockJpaRepositoryCustomImpl implements StockJpaRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<StockEntity> findStock(StockDomainDto.StockSearchDto searchDto) {
        return Optional.ofNullable(
                queryFactory.selectFrom(stockEntity)
                        .where(
                                stockEntity.delete.isFalse(),
                                stockIdEq(searchDto.stockId()),
                                tickerEq(searchDto.ticker()),
                                stockNameEq(searchDto.stockName()),
                                stockNameKrEq(searchDto.stockNameKr())
                        ).fetchOne()
        );
    }

    BooleanExpression stockIdEq(Long stockId){
        return stockId != null? stockEntity.id.eq(stockId) : null;
    }
    BooleanExpression tickerEq(String ticker){
        return ticker != null ? stockEntity.ticker.eq(ticker):null;
    }
    BooleanExpression stockNameEq(String stockName){
        return stockName != null ? stockEntity.stockName.eq(stockName) : null;
    }
    BooleanExpression stockNameKrEq(String stockNameKr){
        return stockNameKr != null ? stockEntity.stockNameKr.eq(stockNameKr) : null;
    }
}
