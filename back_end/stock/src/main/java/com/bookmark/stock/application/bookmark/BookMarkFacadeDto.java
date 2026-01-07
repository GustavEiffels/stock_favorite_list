package com.bookmark.stock.application.bookmark;

import com.bookmark.stock.domain.bookmark.BookMarkDomainDto;

public record BookMarkFacadeDto() {

    public record CreateBookMarkCriteria(
            Long memberId,
            String title
    ){
        public BookMarkDomainDto.CreateBookMarkDto toDomain(){
            return new BookMarkDomainDto.CreateBookMarkDto(memberId,title);
        }
    }
}
