package com.example.ssafyspringstudy.controller.dto;

public class PostResponse {
    //dto - data transfer object => 데이터를 안전하게 이동하기 위한 택배 박스
    public Long id;
    public String title;
    public String content;
    public String author;

    public PostResponse(Long id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
