package com.be.error.exception;


import com.be.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidJWTTokenException.class)
    public ResponseEntity<?> handleUserNotExistException(InvalidJWTTokenException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus()).body(ApiResponse.error(e.getMessage()));
    }

    @ExceptionHandler(UserNotExistException.class)
    public ResponseEntity<?> handleUserNotExistException(UserNotExistException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus()).body(ApiResponse.error(e.getMessage()));
    }

    // IllegalArgumentException 처리
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(e.getMessage()));
    }

    // @Valid 유효성 검사 실패 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(errorMessage));
    }
}
