package com.process.enrolment.repository;

import com.process.enrolment.entity.LectureEnrollTerm;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface LectureEnrollTermRepository extends CrudRepository<LectureEnrollTerm,Long> {

    @Query("select count(term) from LectureEnrollTerm term where term.enrollStartDate=:startDate and term.enrollEndDate=:endDate")
    int isExists(@Param("startDate")LocalDateTime startDate,@Param("endDate")LocalDateTime endDate);
}
