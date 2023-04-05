package com.process.enrolment.repository;

import com.process.enrolment.entity.Lecture;
import com.process.enrolment.entity.Professor;
import com.process.enrolment.entity.enums.DayOfWeeksKor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LectureRepository extends CrudRepository<Lecture, Long> {

    @Query("select count (l) from Lecture l where l.professor=:professor and l.isDisabled= false")
    int countLectureByProfessor(@Param("professor") Professor professor);

    @Query("select l from Lecture l where l.dayOfWeeks=:dayOfWeeks and l.professor=:professor and l.isDisabled= false ")
    Optional<Lecture> findLectureByProfessorAndDayOfWeeks(
            @Param("dayOfWeeks") DayOfWeeksKor dayOfWeeksKor,
            @Param("professor") Professor professor);
}
