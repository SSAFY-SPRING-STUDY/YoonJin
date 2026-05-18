package com.example.ssafyspringstudy.domain.post.service;

import com.example.ssafyspringstudy.domain.member.entity.MemberEntity;
import com.example.ssafyspringstudy.domain.member.repository.MemberRepository;
import com.example.ssafyspringstudy.domain.post.controller.dto.PostRequest;
import com.example.ssafyspringstudy.domain.post.controller.dto.PostResponse;
import com.example.ssafyspringstudy.domain.post.PostEntity;
import com.example.ssafyspringstudy.domain.post.repository.PostRepository;
import com.example.ssafyspringstudy.global.exception.CustomException;
import com.example.ssafyspringstudy.global.exception.error.ErrorCode;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;



    public PostResponse create(PostRequest request, Long authorId) {
        MemberEntity author = memberRepository.findById(authorId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        PostEntity post = request.toEntity(author);
        PostEntity savedPost = postRepository.save(post);

        return PostResponse.fromEntity(savedPost);

    }

    @Transactional(readOnly = true)
    public List<PostResponse> findAll() {
        List<PostEntity> postList = postRepository.findAll();
        List<PostResponse> responseList = new ArrayList<>();

        for (PostEntity post : postList) {
            PostResponse response = PostResponse.fromEntity(post);
            responseList.add(response);
        }
        return responseList;
    }

    @Transactional(readOnly = true)
    public PostResponse getPostById(Long id) {
        PostEntity post = postRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));


        return PostResponse.fromEntity(post);
        }


    //수정
    //이 부분도 찾아보기
    public PostResponse update(PostRequest request, Long id, Long authorId){
        MemberEntity author = memberRepository.findById(authorId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        PostEntity post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        if (!post.getAuthor().getId().equals(author.getId())) {
            throw new CustomException(ErrorCode.INVALID_PERMISSION);
        }

        post.update(request.getTitle(), request.getContent());

        return PostResponse.fromEntity(post);

    }

    //삭제
    public void delete(Long id, Long authorId){
        MemberEntity author = memberRepository.findById(authorId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        PostEntity post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        if (!post.getAuthor().getId().equals(author.getId())) {
            throw new CustomException(ErrorCode.INVALID_PERMISSION);
        }

        postRepository.delete(post);
    }




}
