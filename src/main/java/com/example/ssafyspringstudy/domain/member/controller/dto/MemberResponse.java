package com.example.ssafyspringstudy.domain.member.controller.dto;

import com.example.ssafyspringstudy.domain.member.entity.MemberEntity;

public record MemberResponse(
        Long id,
        String loginId,
        //String password,
        String name
){

    public static MemberResponse fromEntity(MemberEntity post){
        return new MemberResponse(
                post.getId(),
                post.getLoginId(),
                post.getName()

        );
    }
}
