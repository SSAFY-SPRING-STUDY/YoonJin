package com.example.ssafyspringstudy.domain.post;

import com.example.ssafyspringstudy.domain.member.entity.MemberEntity;
import lombok.Getter;
//db에 직접적으로 들어가는 것
@Getter
public class PostEntity {

    private static Long AUTO_INCREMENT = 1L;


    private Long id;
    private String title;
    private String content;
    private MemberEntity author;

    public PostEntity( String title, String content, MemberEntity author){
        this.id = AUTO_INCREMENT++;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public static PostEntity create(String title, String content, MemberEntity author) {
        return new PostEntity(title, content, author);
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

//엔티티 값은 절대 컨트롤러에 넘어가지 않음


}


