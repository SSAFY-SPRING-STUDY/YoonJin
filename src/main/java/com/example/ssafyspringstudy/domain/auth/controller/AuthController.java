package com.example.ssafyspringstudy.domain.auth.controller;

import com.example.ssafyspringstudy.domain.auth.controller.dto.LoginRequest;
import com.example.ssafyspringstudy.domain.auth.controller.dto.LoginResponse;
import com.example.ssafyspringstudy.domain.auth.service.AuthService;
import com.example.ssafyspringstudy.domain.auth.util.Authorizationutils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        LoginResponse response = null;
        try{
            response = authService.login(request);

        }
        catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
        public ResponseEntity<Void> logout(@RequestHeader("Authorization") String authHeader){
            //authHeader 를 토큰만 받아오는게 아니라 BEARER, 토큰(랜덤숫자)로 들어온다.

            String accesstoken  = Authorizationutils.getAccessToken(authHeader);
            authService.logout(accesstoken);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();


        }
}
