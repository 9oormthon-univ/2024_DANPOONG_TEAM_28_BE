package com.be.routine.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.be.routine.entity.Routine;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class RoutineResponseDto {
    private int routineId;
    private String name;
    private List<Integer> repeatDayList;
    //    private List<String> alarmTimeList;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private List<LocalTime> alarmTimeList;

    private Boolean isAlarm;
    private Boolean isRepeat;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public RoutineResponseDto(Routine routine) {
        this.routineId = routine.getId();
        this.name = routine.getName();
        this.repeatDayList = (routine.getRepeatDay() == null || routine.getRepeatDay().isEmpty())
                ? Collections.emptyList()
                : Arrays.stream(routine.getRepeatDay().split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
//        this.alarmTimeList = (routine.getAlarmTime() == null || routine.getAlarmTime().isEmpty())
//                ? Collections.emptyList()
//                : Arrays.asList(routine.getAlarmTime().split(","));
        this.alarmTimeList = routine.getAlarmTimes();
        //Boolean값 처리 equals(Y: true, N:false)
        this.isAlarm = "Y".equals(routine.getIsAlarm());
        this.isRepeat = "Y".equals(routine.getIsRepeat());
        this.startDate = routine.getStartDate();
        this.endDate = routine.getEndDate();
        this.createDate = routine.getCreateDate();
        this.modifyDate = routine.getModifyDate();
    }
}
