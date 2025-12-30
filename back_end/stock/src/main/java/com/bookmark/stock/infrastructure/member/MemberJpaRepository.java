package com.bookmark.stock.infrastructure.member;

import com.bookmark.stock.common.repository.SoftDeleteRepository;
import com.bookmark.stock.domain.member.MemberEntity;

public interface MemberJpaRepository extends SoftDeleteRepository<MemberEntity,Long>,MemberJpaRepositoryCustom {
}
