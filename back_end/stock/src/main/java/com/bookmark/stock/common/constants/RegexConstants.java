package com.bookmark.stock.common.constants;


public interface RegexConstants {

    String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    String EMAIL_MESSAGE = "이메일 형식에 맞지 않습니다.";
    String EMAIL_NOT_NULL_MESSAGE = "이메일은 필수입니다.";

    String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)(?=(?:.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]){2,}).{8,}$";
    String PASSWORD_MESSAGE = "비밀번호는 8자리 이상, 특수문자 2개 이상이 포함되어야 합니다.";
    String PASSWORD_NOT_NULL_MESSAGE = "비밀번호는 필수입니다.";
}
