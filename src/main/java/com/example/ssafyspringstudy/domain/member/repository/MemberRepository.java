package com.example.ssafyspringstudy.domain.member.repository;

import com.example.ssafyspringstudy.domain.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    public MemberEntity save(MemberEntity memberEntity);
    public Optional<MemberEntity> findById(Long memberId);
    public Optional<MemberEntity> findByLoginId(String loginId);

}


