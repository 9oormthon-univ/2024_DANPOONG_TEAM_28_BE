package com.be.mission.service;


import com.be.mission.dto.response.MissionListDto;
import com.be.mission.entity.Mission;
import com.be.mission.repository.MissionRepository;
import com.be.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class MissionService {

    private final MissionRepository missionRepository;

    //1. 맞춤형 미션 3개 조회 (중, 하 미션 중 랜덤제공 )
    public List<MissionListDto> getMissionList(CustomUserDetails userDetails){
        //사용자 난이도 확인

        List<Mission> missions = missionRepository.findRandomThreeMissions();

        return missions.stream()
                .map(mission -> MissionListDto.builder()
                        .id(mission.getId())
                        .name(mission.getName())
                        .category(mission.getCategory())
                        .level(mission.getLevel())
                        .build()).toList();
    }


    //


}
