package com.example.ssafyspringstudy.domain.member.controller;


import com.example.ssafyspringstudy.domain.auth.service.AuthService;
import com.example.ssafyspringstudy.domain.auth.util.Authorizationutils;
import com.example.ssafyspringstudy.domain.member.controller.dto.MemberRequest;
import com.example.ssafyspringstudy.domain.member.controller.dto.MemberResponse;
import com.example.ssafyspringstudy.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;
    private final AuthService authService;


    @PostMapping()
    public ResponseEntity<MemberResponse> join(@RequestBody MemberRequest request){
        MemberResponse response = memberService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);


    }


    @GetMapping("/me")
    public ResponseEntity<MemberResponse> me(@RequestHeader("Authorization") String authHeader){
        MemberResponse response = null;
        try{
            String accessToken = Authorizationutils.getAccessToken(authHeader);
            Long memberId = authService.getMemberId(accessToken);

            response = memberService.findById(memberId);

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(response);
    }
}
