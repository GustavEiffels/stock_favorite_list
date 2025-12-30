package com.bookmark.stock.infrastructure.member;

import com.bookmark.stock.domain.member.MemberEntity;
import com.bookmark.stock.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {
    private final MemberJpaRepository jpaRepository;


    @Override
    public MemberEntity save(MemberEntity memberEntity) {
        return jpaRepository.save(memberEntity);
    }
    @Override
    public Optional<MemberEntity> findMember(String email) {
        return jpaRepository.findByEmail(email);
    }
    @Override
    public Optional<MemberEntity> findMemberWithApiKey(String apiKey) {
        return jpaRepository.findByApiKey(apiKey);
    }
}
