package com.be.category.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //미션 카테고리 pk

    @Column(name = "NAME", length = 10, nullable = false)
    private String name; //미션 카테고리 이름
}
