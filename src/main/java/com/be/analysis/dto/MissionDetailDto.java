package com.be.analysis.dto;

import com.be.userMission.entity.UserMission;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

//하나의 미션(일반 미션이든 마이미션이든)을 클릭하면 나오는 수행한 미션 관련된 이름, 리뷰, 사진 ..등
//즉, MemberMission(사용자가 수행한 미션) 테이블에 저장되는 모든 미션들->missionId

@Data
public class MissionDetailDto {
    private int missionId;
    private String name;
    private String review;
//    private String photo;
    //------------
//    private String stamp;
    private LocalDate date; //yyyy-MM-dd

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime time; //HH:mm

    public MissionDetailDto(UserMission userMission) {
        this.missionId = userMission.getId();

        //Mission 번호가 있는 경우 이름은 Mission에서 가져오고 나머지는 user_mission에서 가져옴
        if (userMission.getMission() != null) {
            this.name = userMission.getMission().getName(); //Mission의 이름
        }
        //Routine 번호가 있는 경우 이름은 Routine에서 가져옴
        else if (userMission.getRoutine() != null) {
            this.name = userMission.getRoutine().getName(); //Routine의 이름
        } else {
            this.name = null;
        }

        //photo, review, stamp는 항상 user_mission에서 가져옴
        this.review = userMission.getReview();
//        this.photo = userMission.getPhoto();
        //------------
//        this.stamp = userMission.getStamp();

        if (userMission.getMissionDate() != null) {
            LocalDateTime missionDateTime = userMission.getMissionDate();
            this.date = missionDateTime.toLocalDate(); //yyyy-MM-dd
            this.time = missionDateTime.toLocalTime(); //HH:mm
        } else {
            this.date = null;
            this.time = null;
        }
    }
}
