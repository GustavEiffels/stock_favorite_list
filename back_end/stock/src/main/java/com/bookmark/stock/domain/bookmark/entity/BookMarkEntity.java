package com.bookmark.stock.domain.bookmark.entity;


import com.bookmark.stock.common.constants.MessageConstants;
import com.bookmark.stock.common.entity.BaseEntity;
import com.bookmark.stock.common.exceptions.BaseException;
import com.bookmark.stock.domain.bookmark.BookMarkException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.regex.Pattern;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BookMark")
public class BookMarkEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long memberId;
    private String title;

    public static void validCheck(Long memberId, String title){
        if(memberId == null){
            throw new BaseException(BookMarkException.MemberIdNullException);
        }
        if(title.isEmpty()){
            throw new BaseException(BookMarkException.TitleNullException);
        }
        if(Pattern.matches(MessageConstants.BOOKMARK_TITLE_PATTERN,title)){
            throw new BaseException(BookMarkException.TitleRegexException);
        }
    }

    public static BookMarkEntity create(Long memberId, String title){
        return new BookMarkEntity(null,memberId,title);
    }
}
