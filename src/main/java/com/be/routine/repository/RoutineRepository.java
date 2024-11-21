package com.be.routine.repository;

import com.be.routine.entity.Routine;
import com.be.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoutineRepository extends JpaRepository<Routine, Integer> {
    //    루틴 "페이징 처리" 목록조회
    Page<Routine> findByUser(User user, Pageable pageable);

    //        루틴 "전체" 목록조회 - 필요할 시 사용하기 위해서 우선 주석처리
//        AnalysisService에서 "마이 루틴 이름 목록 조회" 메서드를 위해 사용중
    List<Routine> findByUser(User user);


    //    Service에서 루틴추가 부분 예외처리3: 중복된 루틴이름을 작성한 경우
    boolean existsByUserAndName(User user, String name);
}
