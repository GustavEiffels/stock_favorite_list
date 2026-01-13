package com.bookmark.stock.domain.bookmark;

import com.bookmark.stock.common.exceptions.BaseException;
import com.bookmark.stock.domain.bookmark.entity.BmWStockEntity;
import com.bookmark.stock.domain.bookmark.entity.BookMarkEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookMarkService {
    private final BookMarkRepository repository;

// BOOKMARK - INSERT
    public BookMarkEntity createBookMark(BookMarkDomainDto.CreateBookMarkDto createBookMarkDto){
        // bookMark 생성 시 유효성 검증
        BookMarkEntity.validCheck(createBookMarkDto.memberId(),createBookMarkDto.title());

        // BookMark 생성
        return repository.bookMarkSave(createBookMarkDto.toDomain());
    }

// BOOKMARK - SELECT
    public Optional<BookMarkEntity> findBookMark(Long bookMarkId){
        return repository.findBookMarkById(bookMarkId);
    }

    public BookMarkEntity getBookMark(Long bookMarkId){
        return repository.findBookMarkById(bookMarkId)
                .orElseThrow(()->new BaseException(BookMarkException.NotFoundBookMarkException));
    }


// BOOKMARK AND STOCK - INSERT
    public BmWStockEntity createBookMarkWithStock(BookMarkDomainDto.CreateBWStockDto createBWStockDto){
        // 1. 먼저 존재하는지 확인
        repository.findBWStock(createBWStockDto.bookMarkId(), createBWStockDto.stockId())
                .orElseThrow(()->new BaseException(BookMarkException.AlreadyExistStockInThisBookMark));

        // 2. 존재하지 않으면 생성
        return repository.saveBookMarkWithStock(createBWStockDto.toDomain());
    }
}
