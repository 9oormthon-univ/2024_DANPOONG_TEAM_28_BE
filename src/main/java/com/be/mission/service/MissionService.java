package com.be.mission.service;


import com.be.mission.dto.response.DailyMissionListDto;
import com.be.mission.entity.Mission;
import com.be.mission.repository.MissionRepository;
import com.be.security.CustomUserDetails;
import com.be.userMission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class MissionService {

    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;


        // 맞춤형 미션 3개 조회 (중, 하 미션 중 랜덤 제공)
        public List<DailyMissionListDto> getDailyMissionList(Integer firstMissionId, Integer secondMissionId, Integer thirdMissionId, CustomUserDetails userDetails) {
            // 사용자가 이전에 수행했던 미션 ID 조회
            List<Integer> doneMissionIds = userMissionRepository.findAllUserMissionIds();
            System.out.println(doneMissionIds);

            // 제외해야 할 ID 리스트 생성
            List<Integer> excludedIds = new ArrayList<>(doneMissionIds);
            System.out.println(excludedIds);

            if (firstMissionId != null) excludedIds.add(firstMissionId);
            if (secondMissionId != null) excludedIds.add(secondMissionId);
            if (thirdMissionId != null) excludedIds.add(thirdMissionId);

            // 랜덤으로 3개의 미션 조회 (제외 ID와 난이도 조건 반영)
            List<Mission> missions = missionRepository.findRandomThreeMissionsExcludingIds1(excludedIds);
            System.out.println(missions);

            return missions.stream()
                    .map(mission -> DailyMissionListDto.builder()
                            .id(mission.getId())
                            .name(mission.getName())
                            .category(mission.getCategory())
                            .level(mission.getLevel())
                            .isCompleted(mission.getIsCompleted())
                            .build())
                    .collect(Collectors.toList());
        }


    // 완료한 미션 개수 조회

/*    public Integer getCompletedMissionCount(CustomUserDetails userDetails) {
        // 사용자가 이전에 수행했던 미션 ID 조회
        List<Integer> doneMissionIds = userMissionRepository.findAllUserMissionIds();
        System.out.println(doneMissionIds);

        // 제외해야 할 ID 리스트 생성
        List<Integer> excludedIds = new ArrayList<>(doneMissionIds);
        System.out.println(excludedIds);

        if (firstMissionId != null) excludedIds.add(firstMissionId);
        if (secondMissionId != null) excludedIds.add(secondMissionId);
        if (thirdMissionId != null) excludedIds.add(thirdMissionId);

        // 랜덤으로 3개의 미션 조회 (제외 ID와 난이도 조건 반영)
        List<Mission> missions = missionRepository.findRandomThreeMissionsExcludingIds1(excludedIds);
        System.out.println(missions);

        return missions.stream()
                .map(mission -> DailyMissionListDto.builder()
                        .id(mission.getId())
                        .name(mission.getName())
                        .category(mission.getCategory())
                        .level(mission.getLevel())
                        .isCompleted(mission.getIsCompleted())
                        .build())
                .collect(Collectors.toList());
    }*/

    }






