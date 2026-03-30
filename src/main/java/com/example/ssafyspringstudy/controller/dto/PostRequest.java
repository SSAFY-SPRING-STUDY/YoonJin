package com.example.ssafyspringstudy.controller.dto;

import lombok.Getter;

//setter는 사용하지 않는다.
@Getter
public class PostRequest {
    private final String title;
    private final String content;
    private final String author;


    public PostRequest(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this. author = author;
    }
}
