package com.be.mission.controller;


import com.be.ApiResponse;
import com.be.mission.dto.response.DailyMissionListDto;
import com.be.mission.service.MissionService;
import com.be.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/missions")
public class MissionController {
    private final MissionService missionService;

    //맞춤형 미션 3개 조회
    @GetMapping("")
    public ResponseEntity<?> getDailyMissionList(@RequestParam(value = "firstMissionId", required = false) Integer firstMissionId,@RequestParam(value = "secondMissionId", required = false) Integer secondMissionId,@RequestParam(value = "thirdMissionId", required = false) Integer thirdMissionId,   CustomUserDetails userDetails) throws Exception {
        List<DailyMissionListDto> response = missionService.getDailyMissionList( firstMissionId,secondMissionId,thirdMissionId,userDetails);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    //현재까지 완료한 미션 게수
  /*  @GetMapping("")
    public ResponseEntity<?> getCompletedMissionCount(CustomUserDetails userDetails) throws Exception {
        List<DailyMissionListDto> response = missionService.getCompletedMissionCount(userDetails);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
*/






}
