package com.be.mission.repository;

import com.be.mission.entity.Mission;
import com.be.userMission.entity.UserMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Integer> {

    //난이도 '상' 제외
    /*@Query("SELECT m FROM Mission m WHERE m.level <= 3 ORDER BY FUNCTION('RAND') LIMIT 3")
    List<Mission> findRandomThreeMissions();*/

    @Query("SELECT m FROM Mission m  ORDER BY FUNCTION('RAND') LIMIT 3")
    List<Mission> findRandomThreeMissions();

}
