package com.be.mission.entity;

import com.be.BaseTimeEntity;
import com.be.category.entity.Category;
import jakarta.persistence.*;
import lombok.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "mission")
public class Mission extends BaseTimeEntity {

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


    //미션 수행 어려움
    @Column(name = "DIFFICULTY", nullable = false)
    @Enumerated(EnumType.STRING)
    private MissionDifficulty missionDifficulty;


    @Column(name = "PHOTO", length = 255)
    private String photo; //미션 사진

    @Column(name = "STAMP", length = 255)
    private String stamp; //미션 도장

    //미션 난이도
    @Column(name = "LEVEL", length = 255)
    private Integer level;

    @Builder
    public Mission(Integer id, Category category, String name,String review, MissionDifficulty missionDifficulty,String photo,String stamp,Integer level) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.review = review;
        this.missionDifficulty = missionDifficulty;
        this.photo= photo;
        this.stamp= stamp;
        this.level= level;
    }

}