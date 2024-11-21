package com.be.error.exception;

import com.be.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UnsupportedJWTTokenException extends RuntimeException{
    private final ErrorCode errorCode;
}
