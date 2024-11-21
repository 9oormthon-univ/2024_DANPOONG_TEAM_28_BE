package com.be;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {

    private static final String SUCCESS_STATUS = "success";
    private static final String FAIL_STATUS = "fail";
    private static final String ERROR_STATUS = "error";

    private String status;
    private T data;
    private String message;

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(SUCCESS_STATUS, data, null);
    }

    public static ApiResponse<?> successWithNoContent() {
        return new ApiResponse<>(SUCCESS_STATUS, null, null);
    }

    public static ApiResponse<?> fail(BindingResult bindingResult) {
        Map<String, String> errorList = new HashMap<>();

        List<ObjectError> errors = bindingResult.getAllErrors();
        for (ObjectError error: errors) {
            if (error instanceof FieldError) {
                errorList.put(((FieldError) error).getField(), error.getDefaultMessage());
            } else {
                errorList.put(error.getObjectName(), error.getDefaultMessage());
            }
        }

        return new ApiResponse<>(FAIL_STATUS, errors, null);
    }

    public static ApiResponse<?> error(String message) {
        return new ApiResponse<>(ERROR_STATUS, null, message);
    }
}