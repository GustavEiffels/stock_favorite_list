package com.bookmark.stock.infrastructure.member;

import com.bookmark.stock.domain.member.MemberEntity;

import java.util.Optional;

public interface MemberJpaRepositoryCustom {

    Optional<MemberEntity> findByEmail(String email);

    Optional<MemberEntity> findByApiKey(String apiKey);

    Optional<MemberEntity> findByMemberId(Long memberId);
}
