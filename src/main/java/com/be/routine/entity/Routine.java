package com.be.routine.entity;

import com.be.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "routine")
public class Routine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false) //외래키
    private User user;

    @Column(name = "NAME", length = 15)
    private String name;

    @Column(name = "IS_ALARM", length = 1)
    private String isAlarm = "N";

    @Column(name = "REPEAT_DAY", length = 20)
    private String repeatDay;

    @Column(name = "IS_REPEAT", length = 1)
    private String isRepeat = "N";

    @Column(name = "START_DATE")
    private LocalDate startDate;

    @Column(name = "END_DATE")
    private LocalDate endDate;

//    @Column(name = "ALARM_TIME", length = 100)
//    private String alarmTime;

    //alarmTime을 LocalTime으로 받으면서, 따로 테이블을 뺌. -> routineId: 1 -> 09:00, 14:00 ... routineId: 2 -> 10:00 ...
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "routine_alarm_time", joinColumns = @JoinColumn(name = "ROUTINE_ID"))
    @Column(name = "ALARM_TIME")
    private List<LocalTime> alarmTimes;

    @Column(name = "CREATE_DATE", updatable = false)
    private LocalDateTime createDate = LocalDateTime.now();

    @Column(name = "MODIFY_DATE")
    private LocalDateTime modifyDate = LocalDateTime.now();

}

