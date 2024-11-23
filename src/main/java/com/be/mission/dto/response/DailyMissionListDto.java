package com.be.mission.dto.response;

import com.be.category.entity.Category;
import lombok.Builder;
import lombok.Getter;


@Getter
public class DailyMissionListDto {

    //미션 리스트 조회

    private final Integer id;

    private final Category category;

    //제목
    private final String name; //미션 이름

    //난이도
    private final Integer level;

    //미션 완료 여부
    private final Boolean isCompleted;

    @Builder
    public DailyMissionListDto(Integer id, Category category, String name, Integer level, Boolean isCompleted){
        this.id = id;
        this.name = name;
        this.category = category;
        this.level=level;
        this.isCompleted=isCompleted;
    }


}

