package com.example.ssafyspringstudy.domain.post.repository;

import com.example.ssafyspringstudy.domain.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long> {
    List<PostEntity> postList = new ArrayList<>();


    public PostEntity save(PostEntity postEntity);

    public List<PostEntity> findAll();

    public Optional<PostEntity> findById(Long id);

    public void delete(PostEntity postEntity);

    public void deleteById(Long id);



}
