package com.bookmark.stock.common.encoder;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {
    public String encode(String rawString){
        return BCrypt.hashpw(rawString,BCrypt.gensalt());
    }
    public boolean matches(String rawString, String encodedString){
        return BCrypt.checkpw(rawString,encodedString);
    }
}
