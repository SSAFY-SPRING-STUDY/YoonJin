package com.example.ssafyspringstudy.global.exception;

import com.example.ssafyspringstudy.global.exception.error.ErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {

        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
