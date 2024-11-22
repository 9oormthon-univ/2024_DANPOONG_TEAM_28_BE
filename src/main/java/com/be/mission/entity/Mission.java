package com.be.mission.entity;

import com.be.category.entity.Category;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "mission")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //미션 pk

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID", nullable = false) //외래키
    private Category category; //미션 카테고리

    @Column(name = "NAME", length = 20, nullable = false)
    private String name; //미션 이름

    @Column(name = "REVIEW", length = 50)
    private String review; //미션 리뷰

    @Column(name = "PHOTO", length = 255)
    private String photo; //미션 사진

    @Column(name = "STAMP", length = 255)
    private String stamp; //미션 도장
}
