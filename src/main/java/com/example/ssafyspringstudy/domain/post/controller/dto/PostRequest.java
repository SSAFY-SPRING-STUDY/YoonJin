package com.example.ssafyspringstudy.domain.post.controller.dto;

import com.example.ssafyspringstudy.domain.member.entity.MemberEntity;
import com.example.ssafyspringstudy.domain.post.PostEntity;
import lombok.Getter;

//setter는 사용하지 않는다.
@Getter
public class PostRequest {
    private final String title ;
    private final String content;



    public PostRequest(String title, String content) {
        this.title = title;
        this.content = content;

    }
    public PostEntity toEntity(MemberEntity author) {
        return PostEntity.create(title,content,author);
    }
}
