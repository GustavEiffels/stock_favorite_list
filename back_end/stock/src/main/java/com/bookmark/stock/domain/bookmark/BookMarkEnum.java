package com.bookmark.stock.domain.bookmark;

import com.bookmark.stock.common.constants.MessageConstants;

public interface BookMarkEnum {

    enum Regex{
        BOOKMARK_TITLE_REGEX(
                MessageConstants.BOOKMARK_TITLE_PATTERN,
                MessageConstants.BOOKMARK_TITLE_MESSAGE
        );
        Regex(String regexRule,String regexMessage){
            this.regexRule = regexRule;
            this.regexMessage = regexMessage;
        }

        public final String regexRule;
        public final String regexMessage;
    }
}
