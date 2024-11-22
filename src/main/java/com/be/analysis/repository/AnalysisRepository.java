package com.be.analysis.repository;

import com.be.userMission.entity.UserMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnalysisRepository extends JpaRepository<UserMission, Integer> {

    // 요일별 활동 개수 카운트
    @Query(value = "SELECT " +
            "MONTH(mission_date) AS month, " +
            "CEIL(DAYOFMONTH(mission_date) / 7) AS week, " +
            "MOD(DAYOFWEEK(mission_date) + 5, 7) AS day, " +
            "COUNT(*) AS count " +
            "FROM user_mission " +
            "WHERE user_id = :userId " +
            "AND YEAR(mission_date) = :year " +
            "AND MONTH(mission_date) = :month " +
            "AND CEIL(DAYOFMONTH(mission_date) / 7) = :week " +
            "GROUP BY month, week, day " +
            "ORDER BY month, week, day",
            nativeQuery = true)
    List<Object[]> findWeeklyReport(
            @Param("userId") Integer userId,
            @Param("year") int year,
            @Param("month") int month,
            @Param("week") int week
    );

    // 특정 요일 활동 목록 상세 조회
    @Query(value = "SELECT * FROM user_mission um " +
            "WHERE um.user_id = :userId " +
            "AND YEAR(um.mission_date) = :year " +
            "AND MONTH(um.mission_date) = :month " +
            "AND CEIL(DAYOFMONTH(um.mission_date) / 7) = :week " +
            "AND MOD(DAYOFWEEK(um.mission_date) + 5, 7) = :day", nativeQuery = true)
    List<UserMission> findDayDetailList(
            @Param("userId") Integer userId,
            @Param("year") int year,
            @Param("month") int month,
            @Param("week") int week,
            @Param("day") int day
    );
}