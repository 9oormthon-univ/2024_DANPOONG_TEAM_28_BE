package com.be.mypage.service;

import com.be.error.ErrorCode;
import com.be.error.exception.UserNotExistException;
import com.be.mypage.dto.MypageResponseDto;
import com.be.routine.dto.RoutineResponseDto;
import com.be.routine.entity.Routine;
import com.be.routine.repository.RoutineRepository;
import com.be.routine.service.RoutineService;
import com.be.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MypageService {

    private final RoutineService routineService;
    private final RoutineRepository routineRepository;

    @Autowired
    public MypageService(RoutineService routineService, RoutineRepository routineRepository) {
        this.routineService = routineService;
        this.routineRepository = routineRepository;
    }

    //    루틴 "페이징처리" 목록조회
    @Transactional(readOnly = true)
    public List<RoutineResponseDto> getPagedRoutine(User user, int page) {
        return routineService.getPagedRoutines(user, page);
    }

//    루틴 "전쳬" 목록조회 - 필요할 시 사용하기 위해서 우선 주석처리
//    @Transactional
//    public List<RoutineResponseDto> getRoutine(User user) {
//        if (user == null) {
//            throw new UserNotExistException(ErrorCode.USER_NOT_EXIST);
//        }
//        return routineService.getRoutine(user);
//    }


    //        멤버 계정 조회
    @Transactional(readOnly = true)
    public MypageResponseDto getAccountInfo(User user) {
        if (user == null) {
            throw new UserNotExistException(ErrorCode.USER_NOT_EXIST);
        }
        return new MypageResponseDto(user.getId(), user.getNickanme(), user.getEmail(), user.getProfileImage());
    }
}
