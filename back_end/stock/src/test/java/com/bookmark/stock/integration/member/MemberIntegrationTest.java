package com.bookmark.stock.integration.member;

import com.bookmark.stock.common.encoder.PasswordEncoder;
import com.bookmark.stock.domain.member.MemberDomainDto;
import com.bookmark.stock.domain.member.MemberEntity;
import com.bookmark.stock.domain.member.MemberRepository;
import com.bookmark.stock.domain.member.MemberService;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

@SpringBootTest
public class MemberIntegrationTest {
    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    MemberDomainDto.CreateMember createMember = new MemberDomainDto.CreateMember(
            "testEmail@test.com",
            "Qwer!234@",
            null
    );

    @Test
    @DisplayName("""
            email, password 는 정규식에 맞게 설정, apikey 는 null 일 때,
            CreateMember 객체를 통해 MemberEntity 생성 
            """)
    void createMember(){
        // given & when
        Assertions.assertThatNoException()
                .isThrownBy(()->memberService.createMember(createMember));

        // then
        Optional<MemberEntity> findMember = memberRepository.findMember(createMember.email());

        Assertions.assertThat(findMember.isPresent()).isTrue();
        Assertions.assertThat(findMember.get().getEmail()).isEqualTo(createMember.email());
        Assertions.assertThat(passwordEncoder.matches(createMember.password(), findMember.get().getPassword())).isTrue();
    }
}
