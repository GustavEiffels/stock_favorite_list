package com.bookmark.stock.infrastructure.bookmark;

import com.bookmark.stock.domain.bookmark.entity.BookMarkEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import static com.bookmark.stock.domain.bookmark.entity.QBookMarkEntity.bookMarkEntity;

@Repository
@RequiredArgsConstructor
public class BookMarkJpaRepositoryCustomImpl implements BookMarkJpaRepositoryCustom{
    public final JPAQueryFactory queryFactory;


    @Override
    public Optional<BookMarkEntity> findActiveBookMarkById(Long bookMarkId) {
        return Optional.ofNullable(queryFactory.selectFrom(bookMarkEntity)
                .where(bookMarkEntity.delete.isFalse().and(bookMarkEntity.id.eq(bookMarkId))).fetchOne());
    }
}
