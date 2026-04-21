package com.example.ssafyspringstudy.domain.member.controller.dto;


import com.example.ssafyspringstudy.domain.member.entity.MemberEntity;

//record를 쓰면 게터랑 requiredConstructor 를 안써줘도 된다. 내장 되어 있음.
public record MemberRequest(
        String loginId,
        String password,
        String name
) {
    public static MemberEntity toEntity(MemberRequest request){
        return new MemberEntity(
                request.loginId(),
                request.password(),
                request.name()
        );
    }
}
