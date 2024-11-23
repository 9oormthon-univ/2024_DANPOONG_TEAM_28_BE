package com.be.mission.repository;

import com.be.mission.entity.Mission;
import com.be.userMission.entity.UserMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Integer> {

    //난이도 상 제외 && 수행했던 미션 제외 && 넘어갈 아이디 제외 한 랜던 3개의 미션 제공
    @Query("SELECT m FROM Mission m WHERE m.id NOT IN :excludedIds AND m.level <= 3 ORDER BY FUNCTION('RAND')")
    List<Mission> findRandomThreeMissionsExcludingIds(@Param("excludedIds") List<Integer> excludedIds);

    @Query("SELECT m FROM Mission m WHERE m.id NOT IN :excludedIds AND m.level <= 3 ORDER BY FUNCTION('RAND')")
    List<Mission> findRandomThreeMissionsExcludingIds1(@Param("excludedIds") List<Integer> excludedIds);




}
