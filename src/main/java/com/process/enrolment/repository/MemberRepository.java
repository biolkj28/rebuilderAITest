package com.process.enrolment.repository;

import com.process.enrolment.entity.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {
    //이메일로 사용자 정보 찾기
    Optional<Member> findMemberByEmail(String email);
    boolean existsMemberByEmail(String email);
}
