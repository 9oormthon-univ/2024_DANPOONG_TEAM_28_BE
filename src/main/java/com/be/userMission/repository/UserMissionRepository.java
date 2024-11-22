package com.be.userMission.repository;

import com.be.userMission.entity.UserMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//UserMissionRepository: 사용자가 수행한 미션(일반미션+마이미션)과 관련해서만 저장하는 UserMission에 대한 Repository입니다

@Repository
public interface UserMissionRepository extends JpaRepository<UserMission, Integer> {

    //이지수-> AnalysisService에서 사용하고 있습니다.
    //루틴ID에 따른 월별 (미션)수행한 날짜 가져오기 -> user_mission 테이블에서 가지고 옴
    @Query(value = "SELECT um FROM UserMission um WHERE um.routine.id = :routineId " +
            "AND YEAR(um.missionDate) = :year " +
            "AND MONTH(um.missionDate) = :month")
    List<UserMission> findByRoutineWithMonth(
            @Param("routineId") Integer routineId,
            @Param("year") int year,
            @Param("month") int month);
}
