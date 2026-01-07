package com.bookmark.stock.domain.stock;

import com.bookmark.stock.common.constants.MessageConstants;
import com.bookmark.stock.common.exceptions.ExceptionInterface;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum StockException implements ExceptionInterface {
    SearchAllAttributeNullException("SearchAllAttributeNullException", MessageConstants.STOCK_SEARCH_ALL_ATTRIBUTE_NULL)
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
