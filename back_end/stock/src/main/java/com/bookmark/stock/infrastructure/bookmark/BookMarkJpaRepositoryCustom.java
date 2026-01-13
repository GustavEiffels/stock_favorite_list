package com.bookmark.stock.infrastructure.bookmark;

import com.bookmark.stock.domain.bookmark.entity.BookMarkEntity;

import java.util.Optional;

public interface BookMarkJpaRepositoryCustom {


    Optional<BookMarkEntity> findActiveBookMarkById(Long bookMarkId);
}
