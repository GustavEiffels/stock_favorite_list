package com.bookmark.stock.common.constants;


public interface MessageConstants {

    String NotFoundMember = "사용자를 찾을 수 없습니다.";
    String InvalidApiKey = "유효하지 않은 API KEY 입니다.";
    String AlreadyUsedEmail = "이미 사용중인 아이디입니다.";
    String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    String EMAIL_MESSAGE = "이메일 형식에 맞지 않습니다.";
    String EMAIL_NOT_NULL_MESSAGE = "이메일은 필수입니다.";

    String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)(?=(?:.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]){2,}).{8,}$";
    String PASSWORD_MESSAGE = "비밀번호는 8자리 이상, 특수문자 2개 이상이 포함되어야 합니다.";
    String PASSWORD_NOT_NULL_MESSAGE = "비밀번호는 필수입니다.";

    String BOOKMARK_MEMBER_NULL_MESSAGE = "사용자 정보가 설정되지 않았습니다.";
    String BOOKMARK_TITLE_PATTERN = "^[a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ\\s_-]{2,50}$";
    String BOOKMARK_TITLE_MESSAGE = "즐겨찾기 이름은 2~50자 여야 합니다.";
    String BOOKMARK_TITLE_NULL_MESSAGE = "즐겨찾기 목록의 이름은 반드시 설정되어야 합니다.";


    //
    String STOCK_SEARCH_ALL_ATTRIBUTE_NULL = "조회 하기 위한 인자 값이 없습니다.";
}
