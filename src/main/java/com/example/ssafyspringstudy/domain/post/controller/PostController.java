package com.example.ssafyspringstudy.domain.post.controller;


import com.example.ssafyspringstudy.domain.auth.service.AuthService;
import com.example.ssafyspringstudy.domain.auth.util.Authorizationutils;
import com.example.ssafyspringstudy.domain.post.controller.dto.PostRequest;
import com.example.ssafyspringstudy.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.ssafyspringstudy.domain.post.controller.dto.PostResponse;
import com.example.ssafyspringstudy.domain.post.service.PostService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;
    private final AuthService authService;

//    public PostController(PostService postService) {
//        this.postService = postService;
//    } //@RequiredArgsConstructor는 필수 인자를 가진 생성자를 자동으로 생성해준다.

    //게시글 생성
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse createPost(@RequestBody PostRequest postRequest, @RequestHeader("Authorization") String authHeader){
       String token = Authorizationutils.getAccessToken(authHeader);
       Long memberId = authService.getMemberId(token);
        PostResponse response = postService.create(postRequest,memberId);
        return ApiResponse.success(response);
    }


    //모든 게시글 조회
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<PostResponse>>findAllPosts(){
        return ApiResponse.success(postService.findAll());
    }


    //특정 게시글 조회
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<PostResponse> findPostById(@PathVariable Long id){
        PostResponse response = postService.getPostById(id);

        return ApiResponse.success(response);
    }

    //게시물 수정
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<PostResponse> updatePost(@PathVariable Long id, @RequestBody PostRequest request,
                                        @RequestHeader("Authorization")String authHeader){

        String token = Authorizationutils.getAccessToken(authHeader);
        Long memberId = authService.getMemberId(token);

        PostResponse response = postService.update(request, id,memberId);
        return ApiResponse.success(response);
    }

    //게시물 삭제
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(
            @PathVariable Long id,
            @RequestHeader("Authorization") String authHeader
    ){
        String token = Authorizationutils.getAccessToken(authHeader);
        Long memberId = authService.getMemberId(token);

        postService.delete(id, memberId);
    }


}
