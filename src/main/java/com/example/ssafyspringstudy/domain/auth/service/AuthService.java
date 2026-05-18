package com.example.ssafyspringstudy.domain.auth.service;

import com.example.ssafyspringstudy.domain.auth.component.SessionManager;
import com.example.ssafyspringstudy.domain.auth.controller.dto.LoginRequest;
import com.example.ssafyspringstudy.domain.auth.controller.dto.LoginResponse;
import com.example.ssafyspringstudy.domain.member.entity.MemberEntity;
import com.example.ssafyspringstudy.domain.member.repository.MemberRepository;
import com.example.ssafyspringstudy.domain.member.service.MemberService;
import com.example.ssafyspringstudy.global.exception.CustomException;
import com.example.ssafyspringstudy.global.exception.error.ErrorCode;
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
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_USERNAME));

        if(member.isValidPassword(request.password())){
            String token = sessionManager.createSession(member.getId());
            return new LoginResponse(token,"Bearer");

        }
        throw new CustomException(ErrorCode.INVALID_PASSWORD);

    }

    public void logout(String accesstoken) {
        sessionManager.removeSession(accesstoken);
    }

    public Long getMemberId(String accessToken) {
        return sessionManager.getMemberId(accessToken).orElseThrow(
                ()->new CustomException(ErrorCode.MEMBER_NOT_FOUND )
        );
    }
}
