package com.bookmark.stock.infrastructure.bookmark;

import com.bookmark.stock.domain.bookmark.BookMarkRepository;
import com.bookmark.stock.domain.bookmark.entity.BookMarkEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookMarkRepositoryImpl implements BookMarkRepository {

    private final BookMarkJpaRepository jpaRepository;
    @Override
    public BookMarkEntity bookMarkSave(BookMarkEntity bookMarkEntity) {
        return jpaRepository.save(bookMarkEntity);
    }
}


