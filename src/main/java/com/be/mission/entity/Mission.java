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


    //미션 난이도
    @Column(name = "LEVEL", length = 255)
    private Integer level;

    @Builder
    public Mission(Integer id, Category category, String name,Integer level) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.level= level;
    }

}
