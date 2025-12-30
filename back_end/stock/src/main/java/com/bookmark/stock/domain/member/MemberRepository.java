package com.bookmark.stock.domain.member;

import java.util.Optional;

public interface MemberRepository {

    MemberEntity save(MemberEntity memberEntity);

    Optional<MemberEntity> findMember(String email);

    Optional<MemberEntity> findMemberWithApiKey(String apiKey);
}
