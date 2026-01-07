package com.bookmark.stock.domain.bookmark;

import com.bookmark.stock.domain.bookmark.entity.BookMarkEntity;

public interface BookMarkRepository {

    BookMarkEntity bookMarkSave(BookMarkEntity bookMarkEntity);
}
