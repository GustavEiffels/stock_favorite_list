package com.bookmark.stock.domain.bookmark;

import com.bookmark.stock.common.constants.MessageConstants;
import com.bookmark.stock.common.exceptions.ExceptionInterface;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BookMarkException implements ExceptionInterface {
    MemberIdNullException("MemberIdNullException", MessageConstants.BOOKMARK_TITLE_NULL_MESSAGE),
    TitleRegexException("TitleRegexException", MessageConstants.BOOKMARK_TITLE_MESSAGE),
    TitleNullException("TitleNullException", MessageConstants.BOOKMARK_TITLE_NULL_MESSAGE),
    AlreadyExistStockInThisBookMark("AlreadyExistStockInThisBookMark",MessageConstants.BMWSTOCK_STOCK_ALREADY_EXIST_IN_BOOKMARK),
    NotFoundBookMarkException("NotFoundBookMarkException",MessageConstants.BOOKMARK_NOT_FOUND);
    ;

    private final String code;
    private final String message;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
