package com.be.mypage.controller;

import com.be.ApiResponse;
import com.be.mypage.dto.MypageResponseDto;
import com.be.mypage.service.MypageService;
import com.be.routine.dto.RoutineRequestDto;
import com.be.routine.dto.RoutineResponseDto;
import com.be.routine.entity.Routine;
import com.be.security.CustomUserDetails;
import com.be.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/myPage")
public class MypageController {

    @Autowired
    private MypageService mypageService;

    //    루틴 "페이징처리" 목록조회 (마이페이지에서 목록조회 가능함)
    @GetMapping("/routines")
    public ResponseEntity<ApiResponse<List<RoutineResponseDto>>> getPagedRoutine(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestParam(value = "page", defaultValue = "0") int page) {
        //로그인한 사용자 정보 가져오기
        User user = userDetails.getUser();

        //페이징 처리된 루틴 가져오기 - mypageService 호출 -> routineService 호출
        List<RoutineResponseDto> routines = mypageService.getPagedRoutine(user, page);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(routines));
    }

//        루틴 "전쳬" 목록조회 (마이페이지에서 목록조회 가능함) - 필요할 시 사용하기 위해서 우선 주석처리
//    @GetMapping("/routines")
//    public ResponseEntity<ApiResponse<List<RoutineResponseDto>>> getRoutine(@AuthenticationPrincipal CustomUserDetails userDetails){
//        //로그인한 사용자의 userId
//        User user = userDetails.getUser();
//        List<RoutineResponseDto> routines = mypageService.getRoutine(user);
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(ApiResponse.success(routines));
//    }

    //    멤버 계정 조회
    @GetMapping("/account")
    public ResponseEntity<ApiResponse<MypageResponseDto>> getAccountInfo(@AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userDetails.getUser(); // 현재 로그인된 사용자 정보 가져오기
        MypageResponseDto accountInfo = mypageService.getAccountInfo(user);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(accountInfo));
    }
}
