package com.bookmark.stock.infrastructure.member;


import com.bookmark.stock.domain.member.MemberEntity;
import com.bookmark.stock.domain.member.QMemberEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Optional;

import static com.bookmark.stock.domain.member.QMemberEntity.memberEntity;

@Repository
@RequiredArgsConstructor
public class MemberJpaRepositoryCustomImpl implements MemberJpaRepositoryCustom {
    private final JPAQueryFactory queryFactory;


    @Override
    public Optional<MemberEntity> findByEmail(String email) {
        return Optional.ofNullable(
                queryFactory.selectFrom(memberEntity)
                        .where(memberEntity.email.eq(email).and(memberEntity.delete.isFalse()))
                        .fetchOne()
        );
    }

    @Override
    public Optional<MemberEntity> findByApiKey(String apiKey) {
        return Optional.ofNullable(
                queryFactory.selectFrom(memberEntity)
                        .where(
                                apiKeyEq(apiKey),
                                memberEntity.delete.isFalse()
                        )
                        .fetchOne()
        );
    }

    BooleanExpression apiKeyEq(String apiKey){
        return apiKey != null ? memberEntity.apiKey.eq(apiKey):null;
    }

}
