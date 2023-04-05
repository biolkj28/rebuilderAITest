package com.process.enrolment.entity;

import com.process.enrolment.entity.common.TimeStamped;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Professor extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long professor_id;

    //User 정보
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    private String departure;
    private String position;

    @Builder
    private Professor(Member member, String departure, String position) {
        this.member = member;
        this.departure = departure;
        this.position = position;
    }
}
