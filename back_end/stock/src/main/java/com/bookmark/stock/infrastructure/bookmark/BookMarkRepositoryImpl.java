package com.bookmark.stock.infrastructure.bookmark;

import com.bookmark.stock.domain.bookmark.BookMarkRepository;
import com.bookmark.stock.domain.bookmark.entity.BmWStockEntity;
import com.bookmark.stock.domain.bookmark.entity.BookMarkEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookMarkRepositoryImpl implements BookMarkRepository {

    private final BookMarkJpaRepository jpaRepository;
    private final BmWStockJpaRepository withJpaRepository;
    @Override
    public BookMarkEntity bookMarkSave(BookMarkEntity bookMarkEntity) {
        return jpaRepository.save(bookMarkEntity);
    }

    @Override
    public Optional<BookMarkEntity> findBookMarkById(Long bookMarkId) {
        return jpaRepository.findActiveBookMarkById(bookMarkId);
    }

    @Override
    public BmWStockEntity saveBookMarkWithStock(BmWStockEntity bmWStockEntity) {
        return withJpaRepository.save(bmWStockEntity);
    }

    @Override
    public Optional<BmWStockEntity> findBWStock(Long bookMarkId, Long stockId) {
        return withJpaRepository.findByBMIdWStockId(bookMarkId,stockId);
    }
}


