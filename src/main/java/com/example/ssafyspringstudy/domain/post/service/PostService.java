package com.example.ssafyspringstudy.domain.post.service;

import com.example.ssafyspringstudy.domain.member.controller.dto.MemberResponse;
import com.example.ssafyspringstudy.domain.member.entity.MemberEntity;
import com.example.ssafyspringstudy.domain.member.repository.MemberRepository;
import com.example.ssafyspringstudy.domain.post.controller.dto.PostRequest;
import com.example.ssafyspringstudy.domain.post.controller.dto.PostResponse;
import com.example.ssafyspringstudy.domain.post.PostEntity;
import com.example.ssafyspringstudy.domain.post.repository.PostRepository;
import com.example.ssafyspringstudy.global.exception.CustomException;
import com.example.ssafyspringstudy.global.exception.error.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;


    public PostResponse create(PostRequest request, Long authorId) {
        MemberEntity author = memberRepository.findById(authorId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        PostEntity entity = request.toEntity(author);
        PostEntity savedEntity = postRepository.save(entity);

        MemberResponse memberResponse = MemberResponse.fromEntity(author);

        return PostResponse.fromEntity(savedEntity,memberResponse);

    }

    public List<PostResponse> findAll() {
        List<PostEntity> entityList = postRepository.findAll();
        List<PostResponse> responseList = new ArrayList<>();

        for (PostEntity entity : entityList) {
            MemberResponse memberResponse = MemberResponse.fromEntity(entity.getAuthor());
            PostResponse response = PostResponse.fromEntity(entity,memberResponse);
            responseList.add(response);
        }
        return responseList;
    }

    public PostResponse getPostById(Long id) {
        PostEntity entity = postRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        MemberResponse memberResponse = MemberResponse.fromEntity(entity.getAuthor());


        return PostResponse.fromEntity(entity,memberResponse);
        }


    //수정
    //이 부분도 찾아보기
    @Transactional
    public PostResponse update(PostRequest request, Long id, Long authorId){
        MemberEntity author = memberRepository.findById(authorId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        PostEntity entity = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        if (!entity.getAuthor().getId().equals(author.getId())) {
            throw new CustomException(ErrorCode.INVALID_PERMISSION);
        }

        entity.update(request.getTitle(), request.getContent());

        MemberResponse memberResponse = MemberResponse.fromEntity(author);
        return PostResponse.fromEntity(entity,memberResponse);

    }

    //삭제
    public void delete(Long id, Long authorId){
        MemberEntity author = memberRepository.findById(authorId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        PostEntity entity = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        if (!entity.getAuthor().getId().equals(author.getId())) {
            throw new CustomException(ErrorCode.INVALID_PERMISSION);
        }

        postRepository.delete(entity);
    }




}
