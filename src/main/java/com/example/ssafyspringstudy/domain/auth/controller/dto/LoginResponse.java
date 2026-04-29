package com.example.ssafyspringstudy.domain.auth.controller.dto;

public record LoginResponse(
        String accessToken,
        String tokenType
) {
}
