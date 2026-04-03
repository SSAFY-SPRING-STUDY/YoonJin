package com.example.ssafyspringstudy.controller;

import com.example.ssafyspringstudy.controller.dto.PostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.ssafyspringstudy.controller.dto.PostResponse;
import com.example.ssafyspringstudy.service.PostService;
import com.example.ssafyspringstudy.repository.createPostRequest;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;


//    public PostController(PostService postService) {
//        this.postService = postService;
//    } //@RequiredArgsConstructor는 필수 인자를 가진 생성자를 자동으로 생성해준다.

    //게시글 생성
    @PostMapping("/api/posts")
    public PostResponse createPost(@RequestBody createPostRequest request){
        PostRequest serviceRequest = new PostRequest(
                request.title,
                request.content,
                request.author
        );


        return postService.save(serviceRequest);
    }


    //모든 게시글 조회
    @GetMapping("/api/posts")
    public List<PostResponse> findAllPosts(){
        return postService.findAll();
    }


    //특정 게시글 조회
    @GetMapping("/api/posts/{id}")
    public PostResponse findPostById(@PathVariable Long id){
        PostResponse response = postService.findById(id);

        return postService.findById(id);
    }

    //게시물 수정
    @PutMapping("/api/posts/{id}")
    public void updatePost(@PathVariable Long id, @RequestBody PostRequest request){
        postService.update(id,request);
    }

    //게시물 삭제
    @DeleteMapping("/api/posts/{id}")
    public void deletePost(@PathVariable Long id){
        postService.delete(id);
    }


}
