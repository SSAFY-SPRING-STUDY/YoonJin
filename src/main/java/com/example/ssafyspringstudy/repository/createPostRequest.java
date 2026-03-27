package com.example.ssafyspringstudy.repository;

public class createPostRequest {
    public String title;
    public String content;
    public String Author;


    @Override
    public String toString() {
        return "createPostRequest{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", Author='" + Author + '\'' +
                '}';
    }
}
