package com.bookmark.stock.domain.member;


import com.bookmark.stock.common.entity.BaseEntity;
import com.bookmark.stock.common.exceptions.BaseException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.regex.Pattern;

import static com.bookmark.stock.common.constants.MessageConstants.EMAIL_PATTERN;
import static com.bookmark.stock.common.constants.MessageConstants.PASSWORD_PATTERN;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Member")
public class MemberEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;
    private String apiKey;

    public static void validCheck(String email,String password){
        if(email == null || email.isEmpty()){
            throw new BaseException(MemberException.EmailNullException);
        }
        if(!Pattern.matches(EMAIL_PATTERN,email)){
            throw new BaseException(MemberException.EmailRegexException);
        }
        if(password == null || password.isEmpty()){
            throw new BaseException(MemberException.PasswordNullException);
        }
        if(!Pattern.matches(PASSWORD_PATTERN,password)){
            throw new BaseException(MemberException.PasswordRegexException);
        }
    }

    public static MemberEntity create(String email, String password, String apiKey){
        return new MemberEntity(
                null,
                email,
                password,
                apiKey
        );
    }
}
