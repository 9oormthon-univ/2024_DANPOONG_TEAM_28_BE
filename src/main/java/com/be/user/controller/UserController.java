package com.be.user.controller;

import com.be.ApiResponse;
import com.be.user.dto.response.UserResponseDto;
import com.be.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;


    @GetMapping("/login") // 인가 코드 받는 테스트용입니다!
    public RedirectView kakaoAuthorizeCode() throws Exception {
        return userService.getKakaoAuthorizeCode();
    }

    @PostMapping("/kakao-login")
    public ResponseEntity<?> kakaoLogin(@RequestParam("code") String code, HttpServletRequest request) throws Exception {
        UserResponseDto.KakaoLoginDTO responseDTO = userService.kakaoLogin(code, request);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(responseDTO));
    }

    //"회원이 소셜 로그인을 마치면 자동으로 실행되는 API입니다. 인가 코드를 이용해 토큰을 받고, 해당 토큰으로 사용자 정보를 조회합니다." +
    //            "사용자 정보를 이용하여 서비스에 회원가입합니다."
    @PostMapping("/validation")
    public ResponseEntity<?> validateJWTToken() {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.successWithNoContent());
    }


}
