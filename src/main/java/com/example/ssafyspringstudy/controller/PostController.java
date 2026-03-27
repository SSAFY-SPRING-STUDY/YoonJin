package com.example.ssafyspringstudy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.ssafyspringstudy.repository.PostResponse;
import com.example.ssafyspringstudy.service.PostService;
import com.example.ssafyspringstudy.repository.createPostRequest;

@RestController
public class PostController {
    private final PostService poseservice;

    @Autowired
    public PostController(PostService poseservice) {
        this.poseservice = poseservice;
    }

    //게시글 생성
    @PostMapping("/api/posts")
    public String createPost(@RequestBody createPostRequest request){
        System.out.println(request);

        PostResponse response = PostService.save(request);


        return "";

    }


}
