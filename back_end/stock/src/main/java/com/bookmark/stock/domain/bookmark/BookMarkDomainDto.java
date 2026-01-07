package com.bookmark.stock.domain.bookmark;

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
}
