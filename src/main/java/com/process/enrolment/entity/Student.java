package com.process.enrolment.entity;

import com.process.enrolment.entity.common.TimeStamped;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //User 정보
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    private int grade;
    private String major;
    @Builder
    private Student(Member member, int grade, String major) {
        this.member = member;
        this.grade = grade;
        this.major = major;
    }
}
