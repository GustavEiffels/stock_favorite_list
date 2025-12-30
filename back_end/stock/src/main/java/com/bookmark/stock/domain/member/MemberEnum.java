package com.bookmark.stock.domain.member;

import com.bookmark.stock.common.constants.RegexConstants;

public interface MemberEnum {

    enum Regex{
        EMAIL_REGEX(
                RegexConstants.EMAIL_PATTERN,
                RegexConstants.EMAIL_MESSAGE
        ),
        PASSWORD_REGEX(
                RegexConstants.PASSWORD_PATTERN,
                RegexConstants.PASSWORD_MESSAGE
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
