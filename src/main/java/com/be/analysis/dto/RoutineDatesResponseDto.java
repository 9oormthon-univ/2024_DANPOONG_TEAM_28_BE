package com.be.analysis.dto;

//마이 루틴 수행한 날짜를 조회할 때 필요한 Response 데이터들

import java.time.LocalDate;
import java.util.List;

public class RoutineDatesResponseDto {
    private int routineId;
    private String routineName;
    private int year;
    private int month;
    private List<LocalDate> completedDates;

    public RoutineDatesResponseDto(int routineId, String routineName, int year, int month, List<LocalDate> completedDates) {
        this.routineId = routineId;
        this.routineName = routineName;
        this.year = year;
        this.month = month;
        this.completedDates = completedDates;
    }

    public int getRoutineId() {
        return routineId;
    }

    public String getRoutineName() {
        return routineName;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public List<LocalDate> getCompletedDates() {
        return completedDates;
    }

    public void setRoutineId(int routineId) {
        this.routineId = routineId;
    }

    public void setRoutineName(String routineName) {
        this.routineName = routineName;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setCompletedDates(List<LocalDate> completedDates) {
        this.completedDates = completedDates;
    }
}
