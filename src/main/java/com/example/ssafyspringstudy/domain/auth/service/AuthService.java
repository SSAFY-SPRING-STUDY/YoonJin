package com.example.ssafyspringstudy.domain.auth.service;

import com.example.ssafyspringstudy.domain.auth.component.SessionManager;
import com.example.ssafyspringstudy.domain.auth.controller.dto.LoginRequest;
import com.example.ssafyspringstudy.domain.auth.controller.dto.LoginResponse;
import com.example.ssafyspringstudy.domain.member.entity.MemberEntity;
import com.example.ssafyspringstudy.domain.member.repository.MemberRepository;
import com.example.ssafyspringstudy.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {


    //로그인 된 상태를 저장해주는 역할
    private final SessionManager sessionManager;
    private final MemberRepository memberRepository;

    public LoginResponse login(LoginRequest request) {
        MemberEntity member = memberRepository.findByLoginId(request.loginId())
                .orElseThrow(() -> new RuntimeException("아이디가 올바르지 않습니다."));

        if(member.isValidPassword(request.password())){
            String token = sessionManager.createSession(member.getId());
            return new LoginResponse(token,"Bearer");

        }
        throw new RuntimeException("비밀번호가 올바르지 않습니다.");

    }

    public void logout(String accesstoken) {
        sessionManager.removeSession(accesstoken);
    }

    public Long getMemberId(String accessToken) {
        return sessionManager.getMemberId(accessToken).orElseThrow(
                ()->new RuntimeException("id값 조회 불가")
        );
    }
}
