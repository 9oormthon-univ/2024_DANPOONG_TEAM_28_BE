package com.be.analysis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//1개의 주차 막대그래프를 위한 클래스

@Data
public class DailyDto {
    private int month; //달
    private int week;  //주차
    private int day;   //요일(0: 월요일 ~ 6: 일요일)
    private long count; //완료한 미션 개수

    public DailyDto(int month, int week, int day, long count) {
        this.month = month;
        this.week = week;
        this.day = day;
        this.count = count;
    }

}


