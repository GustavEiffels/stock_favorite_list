package com.bookmark.stock.domain.bookmark;

import com.bookmark.stock.domain.bookmark.entity.BmWStockEntity;
import com.bookmark.stock.domain.bookmark.entity.BookMarkEntity;

public record BookMarkDomainDto() {

    public record CreateBookMarkDto(
            Long memberId,
            String title
    ){
        public BookMarkEntity toDomain(){
            return BookMarkEntity.create(memberId,title);
        }
    }

    public record CreateBWStockDto(
            Long bookMarkId,
            Long stockId
    ){
        public BmWStockEntity toDomain(){
            return new BmWStockEntity(null,stockId,bookMarkId);
        }
    }
}
