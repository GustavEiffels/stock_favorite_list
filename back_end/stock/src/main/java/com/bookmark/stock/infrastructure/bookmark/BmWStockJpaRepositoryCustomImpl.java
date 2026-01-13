package com.bookmark.stock.infrastructure.bookmark;

import com.bookmark.stock.domain.bookmark.entity.BmWStockEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.bookmark.stock.domain.bookmark.entity.QBmWStockEntity.bmWStockEntity;


@Repository
@RequiredArgsConstructor
public class BmWStockJpaRepositoryCustomImpl implements BmWStockJpaRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    @Override
    public Optional<BmWStockEntity> findByBMIdWStockId(Long bookMarkId, Long stockId) {
        return Optional.ofNullable(
                queryFactory.selectFrom(bmWStockEntity)
                        .where(
                                bmWStockEntity.bookMarkId.eq(bookMarkId)
                                        .and(bmWStockEntity.stockId.eq(stockId))
                        ).fetchOne());
    }
}
