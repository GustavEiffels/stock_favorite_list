package com.bookmark.stock.infrastructure.bookmark;


import com.bookmark.stock.common.repository.SoftDeleteRepository;
import com.bookmark.stock.domain.bookmark.entity.BookMarkEntity;

public interface BookMarkJpaRepository extends SoftDeleteRepository<BookMarkEntity,Long> {
}
