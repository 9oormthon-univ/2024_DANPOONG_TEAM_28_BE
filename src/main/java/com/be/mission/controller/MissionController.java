package com.be.mission.controller;


import com.be.ApiResponse;
import com.be.mission.dto.response.MissionListDto;
import com.be.mission.repository.MissionRepository;
import com.be.mission.service.MissionService;
import com.be.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/missions")
public class MissionController {
    private final MissionService missionService;

    //맞춤형 미션 3개 조회 (중, 하 미션 중 랜덤제공 )

    // 미션 선택 후 미션 홈에 추가
    @GetMapping("")
    public ResponseEntity<?> getMissionList(CustomUserDetails userDetails) throws Exception {
        List<MissionListDto> response = missionService.getMissionList( userDetails);
        return ResponseEntity.ok(ApiResponse.success(response));
    }




}
