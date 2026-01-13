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

    public void addStockAtBookMark(BookMarkFacadeDto.AddStockAtBookMarkCriteria criteria){
        // 1. bookMark 조회  하기 : 실제로 존재 하는지?
        bookMarkService.findBookMark(criteria.bookMarkId());

        // 2. bookMarkId 와 stockId 가 있는 stockWithItem 객체가 존재하는 지 확인 -> stockWithItem 객체 추가
        bookMarkService.createBookMarkWithStock(criteria.toDomainForCreateBmWStock());
    }
}
