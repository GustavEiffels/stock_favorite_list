package com.bookmark.stock.interfaces.api.member;

import com.bookmark.stock.domain.member.MemberDomainDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import static com.bookmark.stock.common.constants.RegexConstants.*;


public record MemberApiDto() {

    public record RequestCreateMember(
            @Pattern(regexp = EMAIL_PATTERN, message = EMAIL_MESSAGE)
            @NotBlank(message = EMAIL_NOT_NULL_MESSAGE)
            String email,
            @Pattern(regexp = PASSWORD_PATTERN,message = PASSWORD_MESSAGE)
            @NotBlank(message = PASSWORD_NOT_NULL_MESSAGE)
            String password,
            String apiKey){
        public MemberDomainDto.CreateMember toDomainDto(){
            return new MemberDomainDto.CreateMember(email,password,apiKey);
        }
    }
}
