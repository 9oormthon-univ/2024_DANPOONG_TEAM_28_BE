package com.be.analysis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

//해당 요일에 수행한 미션들(일반미션+마이미션)을 위한 클래스인 MissionDto를 List로 변환

@Data
@AllArgsConstructor
public class MissionListDto {
    private List<MissionDto> missionList;
}
