package com.bookmark.stock.domain.bookmark;

import com.bookmark.stock.domain.bookmark.entity.BookMarkEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookMarkService {
    private final BookMarkRepository repository;

    // BOOK MARK 생성
    public BookMarkEntity createBookMark(BookMarkDomainDto.CreateBookMarkDto createBookMarkDto){
        // bookMark 생성 시 유효성 검증
        BookMarkEntity.validCheck(createBookMarkDto.memberId(),createBookMarkDto.title());

        // BookMark 생성
        return repository.bookMarkSave(createBookMarkDto.toDomain());
    }
}
