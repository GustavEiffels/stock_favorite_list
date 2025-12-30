package com.bookmark.stock.domain.member;

import com.bookmark.stock.common.constants.RegexConstants;
import com.bookmark.stock.common.exceptions.ExceptionInterface;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MemberException implements ExceptionInterface {
    InvalidApiKey("InvalidKeyException","유효하지 않은 API KEY 입니다."),
    AlreadyUseEmail("AlreadyExistEmail","이미 사용중인 아이디입니다."),
    EmailRegexException("EmailRegexException", RegexConstants.EMAIL_MESSAGE),
    EmailNullException("EmailNullException",RegexConstants.EMAIL_NOT_NULL_MESSAGE),
    PasswordRegexException("PasswordRegexException",RegexConstants.PASSWORD_MESSAGE),
    PasswordNullException("PasswordNullException",RegexConstants.PASSWORD_NOT_NULL_MESSAGE)
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
