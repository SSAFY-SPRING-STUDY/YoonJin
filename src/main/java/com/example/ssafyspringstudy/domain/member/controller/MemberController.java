package com.example.ssafyspringstudy.domain.member.controller;



import com.example.ssafyspringstudy.domain.auth.service.AuthService;
import com.example.ssafyspringstudy.domain.auth.util.Authorizationutils;
import com.example.ssafyspringstudy.domain.member.controller.dto.MemberRequest;
import com.example.ssafyspringstudy.domain.member.controller.dto.MemberResponse;
import com.example.ssafyspringstudy.domain.member.service.MemberService;
import com.example.ssafyspringstudy.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;
    private final AuthService authService;


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<MemberResponse> join(@RequestBody MemberRequest request){
        MemberResponse response = memberService.save(request);
        return ApiResponse.success(response);
    }


    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<MemberResponse> me(@RequestHeader("Authorization") String authHeader){

            String accessToken = Authorizationutils.getAccessToken(authHeader);
            Long memberId = authService.getMemberId(accessToken);
            MemberResponse response = memberService.findById(memberId);
        return ApiResponse.success(response);
    }
}
