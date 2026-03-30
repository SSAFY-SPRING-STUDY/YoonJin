package com.example.ssafyspringstudy.repository;


import lombok.Getter;

@Getter
public class createPostRequest {
    public String title;
    public String content;
    public String author;


    @Override
    public String toString() {
        return "createPostRequest{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
