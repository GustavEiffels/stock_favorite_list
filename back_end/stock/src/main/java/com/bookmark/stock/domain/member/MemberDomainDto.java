package com.bookmark.stock.domain.member;

public record MemberDomainDto() {

    public record CreateMember(
            String email,
            String password,
            String apiKey
    ){

        public MemberEntity toDomain(String encodedPassword){
            return MemberEntity.create(email,encodedPassword,apiKey);
        }
    }
}
