package com.be.analysis.dto;

import com.be.userMission.entity.UserMission;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

//해당 요일에 수행한 미션들(일반미션+마이미션)을 위한 클래스

@Data
public class MissionDto {
    private int missionId;
    private String name;
    private int year;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd")
    private LocalDate date; //yyyy-MM-dd

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime time; //HH:mm

    private String type;
    private int day; //요일(0: 월요일 ~ 6: 일요일)

    public MissionDto(UserMission userMission, int day) {
        this.missionId = userMission.getId();

        //Routine 또는 Mission 해당 이름 가져오기 + Routine인지 Mission인지 명시
        if (userMission.getRoutine() != null && userMission.getRoutine().getId() != null) {
            this.name = userMission.getRoutine().getName();
            this.type = "ROUTINE";
        } else if (userMission.getMission() != null && userMission.getMission().getId() != null) {
            this.name = userMission.getMission().getName();
            this.type = "MISSION";
        } else {
            //Mission과 Routine이 모두 없을 경우 예외 처리
            this.name = "UNKNOWN";
            this.type = "UNKNOWN";
        }

        this.day = day;

//         날짜와 시간을 분리
        if (userMission.getMissionDate() != null) {
            LocalDateTime missionDateTime = userMission.getMissionDate();
            this.year = missionDateTime.getYear();
            this.date = missionDateTime.toLocalDate(); //yyyy-MM-dd
            this.time = missionDateTime.toLocalTime(); //HH:mm
        } else {
            this.year = 0;
            this.date = null; //missionDate가 없으면 null
            this.time = null; //missionTime이 없으면 null
        }
    }
}
