package com.process.enrolment.entity;

import com.process.enrolment.entity.common.TimeStamped;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LectureEnrollTerm extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long term_id;

    private LocalDateTime enrollStartDate;
    private LocalDateTime enrollEndDate;
    @ColumnDefault("false")
    private boolean isOpen;

    @Builder
    private LectureEnrollTerm(LocalDateTime enrollStartDate, LocalDateTime enrollEndDate) {
        this.enrollStartDate = enrollStartDate;
        this.enrollEndDate = enrollEndDate;
    }

    public void open(){
        this.isOpen = true;
    }
}
