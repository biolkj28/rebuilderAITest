package com.process.enrolment.repository;


import com.process.enrolment.entity.Member;
import com.process.enrolment.entity.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

    @Query("select ur from UserRole ur join ur.role where ur.member=:member and ur.isApprove=true")
    Optional<UserRole> findUserRoleByMember(@Param("member")Member member);
}
