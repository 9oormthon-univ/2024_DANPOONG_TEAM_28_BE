package com.be.mission.dto.response;

import com.be.category.entity.Category;
import com.be.mission.entity.Mission;
import com.be.userMission.entity.UserMission;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;


@Getter
public class MissionListDto {

    //미션 리스트 조회

    private final Integer id;

    private final Category category;

    //제목
    private final String name; //미션 이름

    //난이도
    private final Integer level;

    @Builder
    public MissionListDto(Integer id, Category category,String name, Integer level){
        this.id = id;
        this.name = name;
        this.category = category;
        this.level=level;
    }



}

