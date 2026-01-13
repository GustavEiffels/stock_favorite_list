package com.bookmark.stock.domain.bookmark;

import com.bookmark.stock.domain.bookmark.entity.BmWStockEntity;
import com.bookmark.stock.domain.bookmark.entity.BookMarkEntity;

import java.util.Optional;

public interface BookMarkRepository {


// BOOKMARK

    // INSERT
    BookMarkEntity bookMarkSave(BookMarkEntity bookMarkEntity);

    // SELECT
    Optional<BookMarkEntity> findBookMarkById(Long bookMarkId);


// BOOKMARK WITH SOCK
    BmWStockEntity saveBookMarkWithStock(BmWStockEntity bmWStockEntity);

    Optional<BmWStockEntity> findBWStock(Long bookMarkId, Long stockId);
}
