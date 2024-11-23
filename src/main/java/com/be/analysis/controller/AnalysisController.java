package com.be.analysis.controller;

import com.be.ApiResponse;
import com.be.analysis.dto.*;
import com.be.analysis.service.AnalysisService;
import com.be.routine.dto.RoutineResponseDto;
import com.be.security.CustomUserDetails;
import com.be.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/reports")
public class AnalysisController {

    @Autowired
    private AnalysisService analysisService;

    // 요일별 활동 개수 카운트
    @GetMapping("/daily")
    public ResponseEntity<ApiResponse<DailyListDto>> getWeeklyReport(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestParam int year,
            @RequestParam int month,
            @RequestParam int week
    ) {
        //로그인한 사용자의 userId
        User user = userDetails.getUser();
        DailyListDto dailyListDto = analysisService.getWeeklyReport(user, year, month, week);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(dailyListDto));
    }

    // 특정 요일 활동 목록
    @GetMapping("/daily/detailList")
    public ResponseEntity<ApiResponse<MissionListDto>> getDayDetailList(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestParam int year,
            @RequestParam int month,
            @RequestParam int week,
            @RequestParam int day
    ) {
        //로그인한 사용자의 userId
        User user = userDetails.getUser();
        MissionListDto missionListDto = analysisService.getDayDetailList(user, year, month, week, day);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(missionListDto));
    }

    // 특정 요일 활동 목록 상세조회: 날짜, 제목, 사진, 리뷰..등
    @GetMapping("/daily/detail")
    public ResponseEntity<ApiResponse<MissionDetailDto>> getDayDetail(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestParam int missionId){
        //로그인한 사용자의 userId
        User user = userDetails.getUser();
        MissionDetailDto missionDetailDto = analysisService.getDayDetail(user, missionId);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(missionDetailDto));
    }

    //     마이 루틴 이름 목록조회
    @GetMapping("/routine/list")
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> getRoutineList(@AuthenticationPrincipal CustomUserDetails userDetails){
        User user = userDetails.getUser();
        List<Map<String, Object>> routineList = analysisService.getRoutineList(user);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(routineList));
    }

    //    마이 루틴 수행한 날짜 조회
    @GetMapping("/routine/dates")
    public ResponseEntity<ApiResponse<RoutineDatesResponseDto>> getRoutineDates(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestParam Integer routineId,
            @RequestParam int year,
            @RequestParam int month){
        User user = userDetails.getUser();
        RoutineDatesResponseDto routineDatesResponseDto = analysisService.getRoutineDates(user, routineId, year, month);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(routineDatesResponseDto));
    }

    //  특정 월 카테고리별 수행 횟수 조회(TOP3)
    @GetMapping("/month")
    public ResponseEntity<ApiResponse<List<CategoryDto>>> getMonthCategory(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestParam int year,
            @RequestParam int month){
        User user = userDetails.getUser();
        List<CategoryDto> categoryDtoList = analysisService.getMonthCategory(user, year, month);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(categoryDtoList));
    }

}
