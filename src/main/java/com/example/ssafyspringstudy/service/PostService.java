package com.example.ssafyspringstudy.service;

import org.springframework.stereotype.Service;
import com.example.ssafyspringstudy.repository.PostRepository;
import com.example.ssafyspringstudy.repository.createPostRequest;

@Service
public class PostService {
    private final PostRepository postRepository;
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    public s PostResponse save(createPostRequest request) {
        postRepository.save
    }
}
