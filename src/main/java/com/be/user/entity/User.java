package com.be.user.entity;

import com.be.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private Long kakaoId;

    @Column
    private String email;

    @Column
    private String nickanme;

    @Column
    private String profileImage;

    @Builder
    public User(Long kakaoId, String email, String nickname, String profileImage) {
        this.kakaoId = kakaoId;
        this.email = email;
        this.nickanme = nickname;
        this.profileImage = profileImage;
    }
}
