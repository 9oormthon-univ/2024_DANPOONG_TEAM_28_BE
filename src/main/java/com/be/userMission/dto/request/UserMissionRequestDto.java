package com.be.userMission.dto.request;


import com.be.mission.entity.Mission;
import com.be.routine.entity.Routine;
import com.be.user.entity.User;
import com.be.userMission.entity.MissionDifficulty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserMissionRequestDto {

    private Integer missionId; //미션 아이디 , null 허용
    private Integer routineId; //루틴 아이디 , null 허용

    private String review; //미션 리뷰

    private MissionDifficulty missionDifficulty;

}
