package com.bookmark.stock.interfaces.api.bookmark;

public record BookMarkApiDto() {

    public record RequestCreateBookMark(
            Long memberId,
            Long title
    ){}
}
