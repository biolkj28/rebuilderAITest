package com.process.enrolment.entity;

import com.process.enrolment.entity.common.TimeStamped;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Role extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long role_id;
    private String name;

    private String desc;

    private final String roleCode = "ROLECODE_"+ LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));


    public Role(String name, String desc) {
        this.name = "ROLE_"+name.toUpperCase();
        this.desc = desc;
    }
}
