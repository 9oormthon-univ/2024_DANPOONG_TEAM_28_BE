package com.be.userMission.entity;

import com.be.BaseTimeEntity;
import com.be.mission.entity.Mission;
import com.be.routine.entity.Routine;
import com.be.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user_mission")
public class UserMission extends BaseTimeEntity {

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

    //🔍🔍그냥 createdDate 찍으면 되는 걸지도?
    @Column(name = "MISSION_DATE", nullable = false)
    private LocalDateTime missionDate; // 사용자 미션 수행 날짜

    @Column(name = "REVIEW", length = 50)
    private String review; //미션 리뷰


/*    @Column(name = "PHOTO", length = 255)
    private String photo; //미션 사진*/

    @Column(name = "MISSION_DIFFICULTY", nullable = false)
    @Enumerated(EnumType.STRING)
    private MissionDifficulty missionDifficulty;

    @Builder
    public UserMission(Integer id,User user,Mission mission,Routine routine, String review/*String photo*/,MissionDifficulty missionDifficulty){
        this.id=id;
        this.user=user;
        this.mission=mission;
        this.routine=routine;
        this.review=review;
//        this.photo=photo;
        this.missionDifficulty=missionDifficulty;
    }



}

