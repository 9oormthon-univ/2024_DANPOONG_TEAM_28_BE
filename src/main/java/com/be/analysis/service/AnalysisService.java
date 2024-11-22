package com.be.analysis.service;

import com.be.analysis.dto.*;
import com.be.analysis.repository.AnalysisRepository;
import com.be.error.ErrorCode;
import com.be.error.exception.ForbiddenUserException;
import com.be.error.exception.UserNotExistException;
import com.be.userMission.entity.UserMission;
import com.be.userMission.repository.UserMissionRepository;
import com.be.routine.dto.RoutineResponseDto;
import com.be.routine.entity.Routine;
import com.be.routine.repository.RoutineRepository;
import com.be.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnalysisService {

    @Autowired
    private AnalysisRepository analysisRepository;

    @Autowired
    private RoutineRepository routineRepository;

    @Autowired
    private UserMissionRepository userMissionRepository;

    // 요일별 활동 개수 카운트
    @Transactional(readOnly = true)
    public DailyListDto getWeeklyReport(User user, int year, int month, int week) {
        List<Object[]> rawResults = analysisRepository.findWeeklyReport(user.getId(), year, month, week);

//        List<DailyDto> dailyDtos = rawResults.stream()
//                .map(result -> new DailyDto(
//                        ((Number) result[0]).intValue(),  //month
//                        ((Number) result[1]).intValue(),  //week
//                        ((Number) result[2]).intValue(),  //day
//                        ((Number) result[3]).longValue()  //count
//                ))
//                .collect(Collectors.toList());

        Map<Integer, Long> dayToCountMap = rawResults.stream()
                .collect(Collectors.toMap(
                        result -> ((Number) result[2]).intValue(), //day
                        result -> ((Number) result[3]).longValue()  //count
                ));
        List<DailyDto> dailyDtos = new ArrayList<>();
        for (int day = 0; day < 7; day++) {
            long count = dayToCountMap.getOrDefault(day, 0L);
            dailyDtos.add(new DailyDto(month, week, day, count));
        }

        //한 주 미션수행 횟수 평균 구하기
        long totalCount = dailyDtos.stream()
                .mapToLong(DailyDto::getCount)
                .sum();

        double average = 0.0;
        if (!dailyDtos.isEmpty()) { //dailyDtos가 비어 있지 않은 경우에만 계산
            average = Math.round((double) totalCount / dailyDtos.size() * 100.0) / 100.0;
        }

        return new DailyListDto(dailyDtos, average);
    }

    // 특정 요일 활동 목록
    @Transactional(readOnly = true)
    public MissionListDto getDayDetailList(User user, int year, int month, int week, int day) {
        List<UserMission> userMissions = analysisRepository.findDayDetailList(
                user.getId(), year, month, week, day);

        List<MissionDto> missionListDto = userMissions.stream()
                .map(mission -> new MissionDto(mission, day))
                .collect(Collectors.toList());

        return new MissionListDto(missionListDto);
    }

    // 특정 요일 활동 목록 상세조회: 날짜, 제목, 사진, 리뷰..등
    @Transactional(readOnly = true)
    public MissionDetailDto getDayDetail(User user, int missionId) {
        UserMission mission = analysisRepository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("해당 missionId 데이터가 없습니다."));
        if (mission.getUser().getId() != user.getId()) {
            throw new RuntimeException("접근 권한이 없습니다.");
        }
        return new MissionDetailDto(mission);
    }

    //    마이 루틴 이름 목록조회
    @Transactional(readOnly = true)
    public List<Map<String, Object>> getRoutineList(User user) {
        if(user == null){
            throw new UserNotExistException(ErrorCode.USER_NOT_EXIST);
        }
        return routineRepository.findByUser(user)
                .stream()
                .map(routine -> {
                    Map<String, Object> routineInfo = new HashMap<>();
                    routineInfo.put("routineId", routine.getId());
                    routineInfo.put("name", routine.getName());
                    return routineInfo;
                })
                .collect(Collectors.toList());
    }

    //     마이 루틴 수행한 날짜 조회
    @Transactional(readOnly = true)
    public RoutineDatesResponseDto getRoutineDates(User user, Integer routineId, int year, int month) {
        if(user == null){
            throw new UserNotExistException(ErrorCode.USER_NOT_EXIST);
        }

        //루틴의 userId 확인
        Routine routine = routineRepository.findById(routineId)
                .orElseThrow(() -> new IllegalArgumentException("해당 루틴이 존재하지 않습니다."));
        if (routine.getUser().getId() != user.getId()) {
            throw new ForbiddenUserException(ErrorCode.FORBIDDEN_USER);
        }

        //startDate: 월의 첫 날, endDate: 다음 달의 첫 날
        LocalDateTime startDate = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime endDate = startDate.plusMonths(1);

        //마이 루틴 미션을 수행한 날짜(일) 리턴
        List<LocalDate> routineDates = userMissionRepository.findByRoutineWithDateRange(routineId, startDate, endDate)
                .stream()
                .map(UserMission::getMissionDate)
                .map(LocalDateTime::toLocalDate)//memberMission에서 missionDate가 LocalDateTime이기 때문에 에러가 안 나기 위해서 LocalDate로 변환
                .collect(Collectors.toList());

        return new RoutineDatesResponseDto(routine.getId(), routine.getName(), year, month, routineDates);
    }
}


