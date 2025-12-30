package com.bookmark.stock.unit.member;

import com.bookmark.stock.common.encoder.PasswordEncoder;
import com.bookmark.stock.domain.member.MemberDomainDto;
import com.bookmark.stock.domain.member.MemberEntity;
import com.bookmark.stock.domain.member.MemberRepository;
import com.bookmark.stock.domain.member.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class MemberUnitTest {


    MemberDomainDto.CreateMember createMemberDto;

    @BeforeEach
    void setUp(){
        createMemberDto = new MemberDomainDto.CreateMember(
                "test@example.com",
                "PasswordTest123!@",
                "TEST"
        );
    }

    @Test
    @DisplayName("""
            회원 생성 시,
            정규식에 맞는 email, password 입력 시 
            회원 정상적으로 생성 
            """)
    void createMember_00(){

    }
}
