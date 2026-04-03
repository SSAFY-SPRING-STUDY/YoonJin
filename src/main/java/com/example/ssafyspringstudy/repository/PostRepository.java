package com.example.ssafyspringstudy.repository;

import com.example.ssafyspringstudy.entity.PostEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {
    List<PostEntity> postList = new ArrayList<>();

    public PostEntity save(PostEntity postEntity){
        postList.add(postEntity);
        return postEntity;
    }

    public List<PostEntity> findAll(){
        return postList;
    }

    //특정 게시글 상세 조회
    public Optional<PostEntity> findById(Long id){
        //리스트를 다 뒤져본다.
        for(PostEntity post : postList){
            //내가 찾는 id와 게시글의 id가 같다면?
            if(post.getId().equals(id)){
                //찾았으니까 객체 반환
                return Optional.of(post);
            }
        }
        return Optional.empty();

    }
    //게시글 삭제
    public void deleteById(Long id){
        //리스트에서 조건이 맞는 항목 제거
        postList.removeIf(post -> post.getId().equals(id));
    }




}
