package com.bookmark.stock.domain.member;

import com.bookmark.stock.common.encoder.PasswordEncoder;
import com.bookmark.stock.common.exceptions.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createMember(MemberDomainDto.CreateMember createMember){
        // 유효성 검증
        MemberEntity.validCheck(createMember.email(), createMember.password());

        if(repository.findMember(createMember.email()).isPresent()){
            // 이미 사용중인 이메일
            throw new BaseException(MemberException.AlreadyUseEmail);
        }
        if(repository.findMemberWithApiKey(createMember.apiKey()).isPresent()){
            // 이미 사용중인 Api Key
            throw new BaseException(MemberException.InvalidApiKey);
        }
        repository.save(createMember.toDomain(passwordEncoder.encode(createMember.password())));
    }
}
