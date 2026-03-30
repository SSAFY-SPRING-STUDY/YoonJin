package com.example.ssafyspringstudy.entity;

import lombok.Getter;
//db에 직접적으로 들어가는 것
@Getter
public class PostEntity {

    private static Long AUTO_INCREMENT = 1L;


    private Long id;
    private String title;
    private String content;
    private String author;

    public PostEntity( String title, String content, String author){
        this.id = AUTO_INCREMENT++;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

//엔티티 값은 절대 컨트롤러에 넘어가지 않음


}


