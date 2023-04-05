package com.process.enrolment.entity;


import com.process.enrolment.entity.common.TimeStamped;
import com.process.enrolment.entity.enums.DayOfWeeksKor;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lecture extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long lecture_id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DayOfWeeksKor dayOfWeeks;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;
    @Column(nullable = false)
    private int maxPeople;
    @Column(nullable = false)
    private int minPeople;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TERM_ID", nullable = false)
    private LectureEnrollTerm term;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROFESSOR_ID", nullable = false)
    private Professor professor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REGISTER_ID")
    private Member register;


    private boolean isProfessorApprove;
    private boolean isDisabled;

    @Builder
    private Lecture(String name, String description, DayOfWeeksKor dayOfWeeks, LocalTime startTime, LocalTime endTime, int maxPeople, int minPeople, LectureEnrollTerm term, Professor professor, Member register) {
        this.name = name;
        this.description = description;
        this.dayOfWeeks = dayOfWeeks;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxPeople = maxPeople;
        this.minPeople = minPeople;
        this.term = term;
        this.professor = professor;
        this.register = register;
        this.isProfessorApprove = false;
        this.isDisabled = false;
    }

    public void professorApprove(){
        this.isProfessorApprove = true;
    }
}
