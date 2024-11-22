package com.be.analysis.entity;

import com.be.userMission.entity.UserMission;
import com.be.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "analysis")
public class Analysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //사용자 외래키
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    //사용자가 수행한 미션에 해당하는 테이블 외래키
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_MISSION_ID", nullable = false)
    private UserMission userMission;
}
