package com.be.analysis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

//1개의 주차 막대그래프를 위한 클래스인 DailyDto를 List로 변환

@Data
@AllArgsConstructor
public class DailyListDto {
    private List<DailyDto> dailyList;
    private double average; //한 주 미션수행 횟수 평균
}
