package com.be.routine.controller;

import com.be.ApiResponse;
import com.be.routine.dto.RoutineRequestDto;
import com.be.routine.dto.RoutineResponseDto;
import com.be.routine.entity.Routine;
import com.be.routine.repository.RoutineRepository;
import com.be.routine.service.RoutineService;
import com.be.security.CustomUserDetails;
import com.be.security.JWTTokenProvider;
import com.be.user.entity.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/routines")
public class RoutineController {

    @Autowired
    private RoutineService routineService;

    //    루틴 추가
    @PostMapping
    public ResponseEntity<ApiResponse<RoutineResponseDto>> addRoutine(@AuthenticationPrincipal CustomUserDetails userDetails, @Valid @RequestBody RoutineRequestDto routineRequestDto) {
        //로그인한 사용자의 userId
        User user = userDetails.getUser();
        RoutineResponseDto saveRoutine = routineService.addRoutine(user, routineRequestDto);
        return ResponseEntity
                .status(201) //201(CREATED)
                .body(ApiResponse.success(saveRoutine));
    }

    //    루틴 수정
    @PutMapping("/{routineId}")
    public ResponseEntity<ApiResponse<RoutineResponseDto>> updateRoutine(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Integer routineId, @Valid @RequestBody RoutineRequestDto routineRequestDto) {
        //로그인한 사용자의 userId
        User user = userDetails.getUser();
        RoutineResponseDto updateRoutine = routineService.updateRoutine(user, routineId, routineRequestDto);
        return ResponseEntity
                .ok(ApiResponse.success(updateRoutine)); //200(OK)
    }

    //    루틴 삭제
    @DeleteMapping("/{routineId}")
    public ResponseEntity<ApiResponse<Map<String, Object>>> deleteRoutine(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Integer routineId) {
        //로그인한 사용자의 userId
        User user = userDetails.getUser();
        Integer deleteRoutineId = routineService.deleteRoutine(user, routineId);
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("routineId", deleteRoutineId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(responseData));
    }

}
