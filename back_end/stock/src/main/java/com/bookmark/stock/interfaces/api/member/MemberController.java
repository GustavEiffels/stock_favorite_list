package com.bookmark.stock.interfaces.api.member;

import com.bookmark.stock.common.ApiResponse;
import com.bookmark.stock.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public ApiResponse<Void> createMember(@RequestBody MemberApiDto.RequestCreateMember request){
        memberService.createMember(request.toDomainDto());
        return ApiResponse.ok();
    }
}
