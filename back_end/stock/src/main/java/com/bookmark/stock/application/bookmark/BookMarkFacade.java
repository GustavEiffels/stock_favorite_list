package com.bookmark.stock.application.bookmark;

import com.bookmark.stock.domain.bookmark.BookMarkService;
import com.bookmark.stock.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookMarkFacade {
    private final MemberService memberService;
    private final BookMarkService bookMarkService;

    @Transactional
    public void createBookMark(BookMarkFacadeDto.CreateBookMarkCriteria criteria){
        // 유효한 사용자인지 확인
        memberService.existMember(criteria.memberId());
        // BookMark 생성
        bookMarkService.createBookMark(criteria.toDomain());
    }
}
