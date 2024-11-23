package com.be.userMission.service;

import com.be.error.exception.MissionNotExistException;
import com.be.error.exception.RoutineNotExistException;
import com.be.error.exception.UserNotExistException;
import com.be.config.S3Service;
import com.be.error.ErrorCode;
import com.be.mission.entity.Mission;
import com.be.mission.repository.MissionRepository;
import com.be.routine.entity.Routine;
import com.be.routine.repository.RoutineRepository;
import com.be.security.CustomUserDetails;
import com.be.user.entity.User;
import com.be.user.repository.UserRepository;
import com.be.userMission.dto.request.UserMissionRequestDto;
import com.be.userMission.entity.MissionImage;
import com.be.userMission.entity.UserMission;
import com.be.userMission.repository.MissionImageRepository;
import com.be.userMission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class UserMissionService {
    private final UserMissionRepository userMissionRepository;
    private final MissionRepository missionRepository;
    private final RoutineRepository routineRepository;
    private final UserRepository userRepository;
    private final MissionImageRepository missionImageRepository;
    private final S3Service s3Service;


    public void postMissionReview( UserMissionRequestDto request,  MultipartFile image, CustomUserDetails userDetails) throws IOException {
        User user = userRepository.findById(userDetails.getUser().getId()).orElseThrow(() -> new UserNotExistException(ErrorCode.USER_NOT_EXIST));

        Mission mission = missionRepository.findById(request.getMissionId()).orElseThrow(() -> new MissionNotExistException(ErrorCode.MISSION_NOT_EXIST));

        Routine routine = routineRepository.findById(request.getRoutineId()).orElseThrow(() -> new RoutineNotExistException(ErrorCode.ROUTINE_NOT_EXIST));



        UserMission userMission = userMissionRepository.save(
                UserMission.builder()
                        .user(user)
                        .mission(mission)
                        .routine(routine)
                        .review(request.getReview())
                        .missionDifficulty(request.getMissionDifficulty())
                        .build()
        );

        if (image != null && !image.isEmpty()) { // null 체크와 비어있지 않은지 확인
            String url = s3Service.upload(image, "user_mission");

            MissionImage missionImage = MissionImage.builder()
                    .mission(mission)
                    .url(url)
                    .build();

            missionImageRepository.save(missionImage);
        }
    }

}
