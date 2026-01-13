package com.bookmark.stock.application.bookmark;

import com.bookmark.stock.domain.bookmark.BookMarkDomainDto;
import com.bookmark.stock.domain.bookmark.entity.BmWStockEntity;

public record BookMarkFacadeDto() {

    public record CreateBookMarkCriteria(
            Long memberId,
            String title
    ){
        public BookMarkDomainDto.CreateBookMarkDto toDomain(){
            return new BookMarkDomainDto.CreateBookMarkDto(memberId,title);
        }
    }

    public record AddStockAtBookMarkCriteria(
            Long bookMarkId,
            Long stockId
    ){
        public BookMarkDomainDto.CreateBWStockDto toDomainForCreateBmWStock(){
            return new BookMarkDomainDto.CreateBWStockDto(bookMarkId,stockId);
        }
    }
}
