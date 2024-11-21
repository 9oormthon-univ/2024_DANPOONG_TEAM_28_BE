package com.test.lively.routine.dto;

import com.test.lively.routine.entity.Routine;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class RoutineRequestDto {

    @NotNull(message = "루틴 이름은 필수입니다.")
    @NotEmpty(message = "루틴 이름은 비어 있을 수 없습니다.")
    @Size(max = 15, message = "이름은 최대 15자까지 입력 가능합니다.")
    private String name;

    @NotNull(message = "반복 요일 리스트는 필수입니다.")
    private List<@Min(value = 0, message = "요일은 0(월요일) 이상이어야 합니다.")
    @Max(value = 6, message = "요일은 6(일요일) 이하여야 합니다.") Integer> repeatDayList;

    //    private List<String> alarmTimeList;
    private List<LocalTime> alarmTimeList;

    @NotNull(message = "알람 여부는 반드시 입력되어야 합니다.")
    private Boolean isAlarm;

    @NotNull(message = "반복 여부는 반드시 입력되어야 합니다.")
    private Boolean isRepeat;

    @FutureOrPresent(message = "시작 날짜는 과거일 수 없습니다.")
    private LocalDate startDate;

    private LocalDate endDate;


    // 시작 날짜와 종료 날짜 검증
    @AssertTrue(message = "시작 날짜는 종료 날짜보다 늦을 수 없습니다.")
    public boolean isValidDateRange() {
        if (startDate == null || endDate == null) {
            return true;
        }
        return !startDate.isAfter(endDate);
    }
}
