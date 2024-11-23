package com.be.analysis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryDto {

//    특정 월의 TOP3 카테고리별 횟수를 조회하는 DTO

    private Integer categoryId;
    private String categoryName;
    private long count;
}
