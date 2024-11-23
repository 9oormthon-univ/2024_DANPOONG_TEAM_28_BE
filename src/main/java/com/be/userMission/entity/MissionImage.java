package com.be.userMission.entity;

import com.be.mission.entity.Mission;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mission_image")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MissionImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @Column(name = "url", nullable = false)
    private String url;

    @Builder
    public MissionImage(Mission mission, String url) {
        this.mission = mission;
        this.url = url;
    }
}
