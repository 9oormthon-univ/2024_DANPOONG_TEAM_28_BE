package com.be.analysis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

//MissionDetailDto를 List로 변환

@Data
@AllArgsConstructor
public class MissionDetailListDto {
    private List<MissionDetailDto> missionDetailList;
}
