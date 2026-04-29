package com.example.ssafyspringstudy.domain.auth.controller.dto;

public record LoginRequest(
        String loginId,
        String password
) {
}
