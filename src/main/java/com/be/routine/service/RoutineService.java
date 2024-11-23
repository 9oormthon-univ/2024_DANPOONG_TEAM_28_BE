package com.be.routine.service;

import com.be.error.ErrorCode;
import com.be.error.exception.ForbiddenUserException;
import com.be.error.exception.UserNotExistException;
import com.be.routine.dto.RoutineRequestDto;
import com.be.routine.dto.RoutineResponseDto;
import com.be.routine.entity.Routine;
import com.be.routine.repository.RoutineRepository;
import com.be.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoutineService {

    @Autowired
    private RoutineRepository routineRepository;

    //    루틴 추가
    @Transactional
    public RoutineResponseDto addRoutine(User user, RoutineRequestDto routineRequestDto) {
//        예외처리1: 해당 사용자인지 확인
        if (user == null) {
            throw new UserNotExistException(ErrorCode.USER_NOT_EXIST);
        }
//        예외처리2: 중복된 루틴이름을 작성한 경우
        if (routineRepository.existsByUserAndName(user, routineRequestDto.getName())) {
            throw new IllegalArgumentException("이미 동일한 루틴 이름이 존재합니다.");
        }
//        예외처리3: 시작날짜와 종료날짜
        if (routineRequestDto.getStartDate() != null && routineRequestDto.getEndDate() != null) {
            if (routineRequestDto.getStartDate().isAfter(routineRequestDto.getEndDate())) {
                throw new IllegalArgumentException("시작 날짜는 종료 날짜보다 늦을 수 없습니다.");
            }
        }
        if (routineRequestDto.getEndDate() != null && routineRequestDto.getEndDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("종료 날짜는 과거일 수 없습니다.");
        }

        Routine routine = new Routine();
        routine.setUser(user);
        routine.setName(routineRequestDto.getName());
        //repeatDay: null이거나 비어있으면 빈 문자열로 결괏값이 나옴
        String repeatDays;
        if (routineRequestDto.getRepeatDayList() == null || routineRequestDto.getRepeatDayList().isEmpty()) {
            repeatDays = "";
        } else {
            repeatDays = routineRequestDto.getRepeatDayList().stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));
        }
        routine.setRepeatDay(repeatDays);
        //alarmTime: null이거나 비어있으면 빈 문자열로 결괏값이 나옴
//        String alarmTimes;
//        if (routineRequestDto.getAlarmTimeList() == null || routineRequestDto.getAlarmTimeList().isEmpty()) {
//            alarmTimes = "";
//        } else {
//            alarmTimes = String.join(",", routineRequestDto.getAlarmTimeList());
//        }
//        routine.setAlarmTime(alarmTimes);
        routine.setAlarmTimes(routineRequestDto.getAlarmTimeList());
        routine.setIsAlarm(routineRequestDto.getIsAlarm() ? "Y" : "N");
        routine.setIsRepeat(routineRequestDto.getIsRepeat() ? "Y" : "N");
        routine.setStartDate(routineRequestDto.getStartDate());
        routine.setEndDate(routineRequestDto.getEndDate());

        Routine saveRoutine = routineRepository.save(routine);
        return new RoutineResponseDto(saveRoutine);
    }

    //    루틴 수정
    @Transactional
    public RoutineResponseDto updateRoutine(User user, Integer routineId, RoutineRequestDto routineRequestDto) {
//       예외처리1: 해당 사용자인지 확인
        if (user == null) {
            throw new UserNotExistException(ErrorCode.USER_NOT_EXIST);
        }
//        예외처리2: 해당 루틴번호가 없는 경우
        Routine routine = routineRepository.findById(routineId)
                .orElseThrow(() -> new IllegalArgumentException("해당 루틴번호가 존재하지 않습니다."));
//        예외처리3: 로그인한 회원이 루틴을 수정하지 않은 경우
        if (routine.getUser().getId() != user.getId()) {
            throw new ForbiddenUserException(ErrorCode.FORBIDDEN_USER);
        }

        //repeatDay: null이거나 비어있으면 빈 문자열로 결괏값이 나옴
        routine.setName(routineRequestDto.getName());
        String repeatDays;
        if (routineRequestDto.getRepeatDayList() == null || routineRequestDto.getRepeatDayList().isEmpty()) {
            repeatDays = "";
        } else {
            repeatDays = routineRequestDto.getRepeatDayList().stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));
        }
        routine.setRepeatDay(repeatDays);
        //alarmTime: null이거나 비어있으면 빈 문자열로 결괏값이 나옴
//        String alarmTimes;
//        if (routineRequestDto.getAlarmTimeList() == null || routineRequestDto.getAlarmTimeList().isEmpty()) {
//            alarmTimes = "";
//        } else {
//            alarmTimes = String.join(",", routineRequestDto.getAlarmTimeList());
//        }
//        routine.setAlarmTime(alarmTimes);
        routine.setAlarmTimes(routineRequestDto.getAlarmTimeList());
        routine.setIsAlarm(routineRequestDto.getIsAlarm() ? "Y" : "N");
        routine.setIsRepeat(routineRequestDto.getIsRepeat() ? "Y" : "N");
        routine.setStartDate(routineRequestDto.getStartDate());
        routine.setEndDate(routineRequestDto.getEndDate());
        routine.setModifyDate(LocalDateTime.now());

        Routine updateRoutine = routineRepository.save(routine);
        return new RoutineResponseDto(updateRoutine);
    }

    //    루틴 삭제
    @Transactional
    public Integer deleteRoutine(User user, Integer routineId) {
//        예외1: 해당 회원이 아닌 경우
        if (user == null) {
            throw new UserNotExistException(ErrorCode.USER_NOT_EXIST);
        }
//        예외2: 해당 루틴이 존재하지 않는 경우
        Routine routine = routineRepository.findById(routineId)
                .orElseThrow(() -> new IllegalArgumentException("해당 루틴 번호가 존재하지 않습니다."));
//        예외3: 사용자와 routineId 소유자가 다른 경우
        if (routine.getUser().getId() != user.getId()) {
            throw new ForbiddenUserException(ErrorCode.FORBIDDEN_USER);
        }

        routineRepository.deleteById(routineId);

        return routineId;
    }

    //    루틴 "페이징처리" 목록조회
    @Transactional(readOnly = true)
    public List<RoutineResponseDto> getPagedRoutines(User user, int page) {
        if (user == null) {
            throw new UserNotExistException(ErrorCode.USER_NOT_EXIST);
        }

        //루틴 리스트 "4개"씩 보여주기
        Pageable pageable = PageRequest.of(page, 4);
        Page<Routine> routinePage = routineRepository.findByUser(user, pageable);

        //데이터가 없는 경우 예외 처리
        if (routinePage.isEmpty()) {
            throw new IllegalArgumentException("해당 페이지에는 데이터가 존재하지 않습니다.");
        }

        // ********종료 날짜가 현재 날짜보다 이전인 루틴의 알람은 자동으로 끔
        routinePage.getContent().forEach(routine -> {
            LocalDate endDate = routine.getEndDate(); //종료 날짜 가져오기
            if (endDate != null && endDate.isBefore(LocalDate.now())) { //종료 날짜가 null이 아닌 경우에만 isBefore 호출
                routine.setIsAlarm("N");
                routineRepository.save(routine);
            }
        });

        return routinePage.getContent()
                .stream()
                .map(RoutineResponseDto::new)
                .collect(Collectors.toList());
    }

//        루틴 "전쳬" 목록조회 - 필요할 시 사용하기 위해서 우선 주석처리
//    @Transactional(readOnly = true)
//    public List<RoutineResponseDto> getRoutine(User user) {
////        예외처리1: 해당 사용자인지 확인
//        if(user == null){
//            throw new UserNotExistException(ErrorCode.USER_NOT_EXIST);
//        }
//
//        List<Routine> routineList = routineRepository.findByUser(user);
//
//        return routineList.stream()
//                .map(RoutineResponseDto::new)
//                .collect(Collectors.toList());
//    }

}



