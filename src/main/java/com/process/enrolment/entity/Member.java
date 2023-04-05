package com.process.enrolment.entity;

import com.process.enrolment.entity.enums.Gender;
import com.process.enrolment.entity.common.TimeStamped;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long member_id;

    private String realName;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Builder
    private Member(String realName, String email, String password, Gender gender) {
        this.realName = realName;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }
}
