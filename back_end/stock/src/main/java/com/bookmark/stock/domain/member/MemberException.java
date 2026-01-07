package com.bookmark.stock.domain.member;

import com.bookmark.stock.common.constants.MessageConstants;
import com.bookmark.stock.common.exceptions.ExceptionInterface;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MemberException implements ExceptionInterface {
    NotFoundMemberException("NotFoundMemberException", MessageConstants.NotFoundMember),
    InvalidApiKey("InvalidKeyException", MessageConstants.InvalidApiKey),
    AlreadyUseEmail("AlreadyExistEmail", MessageConstants.AlreadyUsedEmail),
    EmailRegexException("EmailRegexException", MessageConstants.EMAIL_MESSAGE),
    EmailNullException("EmailNullException", MessageConstants.EMAIL_NOT_NULL_MESSAGE),
    PasswordRegexException("PasswordRegexException", MessageConstants.PASSWORD_MESSAGE),
    PasswordNullException("PasswordNullException", MessageConstants.PASSWORD_NOT_NULL_MESSAGE)
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
