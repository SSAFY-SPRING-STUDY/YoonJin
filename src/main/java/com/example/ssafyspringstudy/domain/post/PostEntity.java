package com.example.ssafyspringstudy.domain.post;

import com.example.ssafyspringstudy.domain.member.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

//db에 직접적으로 들어가는 것
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String title;
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private MemberEntity author;

    public PostEntity( String title, String content, MemberEntity author){
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


