package com.bookmark.stock.domain.member;

import com.bookmark.stock.common.constants.MessageConstants;

public interface MemberEnum {

    enum Regex{
        EMAIL_REGEX(
                MessageConstants.EMAIL_PATTERN,
                MessageConstants.EMAIL_MESSAGE
        ),
        PASSWORD_REGEX(
                MessageConstants.PASSWORD_PATTERN,
                MessageConstants.PASSWORD_MESSAGE
        )
        ;
        Regex(String regexRule, String regexMessage){
            this.regexRule = regexRule;
            this.regexMessage = regexMessage;
        }
        public final String regexRule;
        public final String regexMessage;
    }
}
