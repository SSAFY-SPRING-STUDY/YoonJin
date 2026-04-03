package com.example.ssafyspringstudy.service;

import com.example.ssafyspringstudy.controller.dto.PostRequest;
import com.example.ssafyspringstudy.controller.dto.PostResponse;
import com.example.ssafyspringstudy.entity.PostEntity;
import com.example.ssafyspringstudy.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PostService {

    private final PostRepository postRepository;


    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public PostResponse save(PostRequest request) {
        PostEntity entity = new PostEntity(request.getTitle(),
                request.getContent(),
                request.getAuthor());
        PostEntity returnedEntity = postRepository.save(entity);


        PostResponse response = new PostResponse(returnedEntity.getId(), returnedEntity.getTitle(), returnedEntity.getContent(), returnedEntity.getAuthor());

        return response;

    }

    public List<PostResponse> findAll() {
        List<PostEntity> entityList = postRepository.findAll();
        List<PostResponse> responseList = new ArrayList<>();

        for (PostEntity entity : entityList) {
            PostResponse response = new PostResponse(entity.getId(),
                    entity.getTitle(),
                    entity.getContent(),
                    entity.getAuthor());
            responseList.add(response);
        }
        return responseList;
    }

    public PostResponse findById(Long id) {
        PostEntity entity = postRepository.findById(id).orElse(null);

        if(entity == null){
            return null;
        }

        return new PostResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getAuthor()
        );

    }
    //수정
    public void update(Long id, PostRequest request){
        postRepository.findById(id).ifPresent(entity->entity.update(request.getTitle(),request.getContent()));
    }

    //삭제
    public void delete(Long id){
        postRepository.deleteById(id);
    }



}
