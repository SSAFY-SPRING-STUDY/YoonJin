package com.example.ssafyspringstudy.domain.post.controller.dto;

import com.example.ssafyspringstudy.domain.member.controller.dto.MemberResponse;
import com.example.ssafyspringstudy.domain.post.PostEntity;

public record PostResponse (
        //dto - data transfer object => 데이터를 안전하게 이동하기 위한 택배 박스
        Long id,
        String title,
        String content,
        MemberResponse memberResponse

){
    public static PostResponse fromEntity(PostEntity entity, MemberResponse memberResponse){
        return new PostResponse(entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                memberResponse
                );
    }
}
