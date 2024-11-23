package com.be.userMission.controller;

import com.be.ApiResponse;
import com.be.security.CustomUserDetails;
import com.be.userMission.dto.request.UserMissionRequestDto;
import com.be.userMission.service.UserMissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/userMission")
public class UserMissionController {
    private final UserMissionService userMissionService;

    // 미션 인증 후 리워드 받기
    //미션 아이디 또는 루틴 아이디 받기
    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> postMissionReview( @RequestPart UserMissionRequestDto request, @RequestPart MultipartFile image, CustomUserDetails userDetails) throws Exception {
        userMissionService.postMissionReview(request,image, userDetails);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

}
