package com.be.userMission.entity;

import com.be.mission.entity.Mission;
import com.be.routine.entity.Routine;
import com.be.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_mission")
public class UserMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //사용자가 수행한 미션 pk

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false) //외래키
    private User user; //사용자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MISSION_ID") //외래키: nullable false 아님(미션번호나 루틴번호 하나만 또는 둘 다 들어가기 때문)
    private Mission mission; //미션

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROUTINE_ID") //외래키: nullable false 아님(미션번호나 루틴번호 하나만 또는 둘 다 들어가기 때문)
    private Routine routine; //루틴

    @Column(name = "MISSION_DATE", nullable = false)
    private LocalDateTime missionDate; // 사용자 미션 수행 날짜
}
