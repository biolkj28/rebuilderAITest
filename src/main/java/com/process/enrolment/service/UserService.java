package com.process.enrolment.service;

import com.process.enrolment.controller.request.SignUpRequest;
import com.process.enrolment.entity.*;
import com.process.enrolment.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;

    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;


    /**
     * 학생 회원가입
     * @param commonMemberInfo : 사용자 공통 정보 객체
     * @param studentInfo : 학생 정보 객체
     */
    @Transactional
    public void signupForStudent(SignUpRequest.CommonMemberInfo commonMemberInfo, SignUpRequest.Student studentInfo) {
        Member member = createMember(commonMemberInfo);
        giveUserRole(member, commonMemberInfo.getAuthorityCode());
        Student student = Student.builder()
                .member(member)
                .grade(studentInfo.getGrade())
                .major(studentInfo.getMajor())
                .build();
        studentRepository.save(student);
    }

    /**
     * 교수 회원 가입
     * @param commonMemberInfo : 사용자 공통 정보 객체
     * @param professorInfo : 교수 정보 객체
     */
    @Transactional
    public void signUpForProfessor(SignUpRequest.CommonMemberInfo commonMemberInfo, SignUpRequest.Professor professorInfo) {
        Member member = createMember(commonMemberInfo);
        giveUserRole(member, commonMemberInfo.getAuthorityCode());
        Professor professor = Professor.builder()
                .member(member)
                .departure(professorInfo.getDeparture())
                .position(professorInfo.getPosition())
                .build();
        professorRepository.save(professor);
    }

    /**
     * 관리자 회원가입
     * @param commonMemberInfo: 사용자 공통 정보 객체
     */
    @Transactional
    public void signUpForAdmin(SignUpRequest.CommonMemberInfo commonMemberInfo){
        Member member = createMember(commonMemberInfo);
        giveUserRole(member, commonMemberInfo.getAuthorityCode());
    }

    /**
     * 사용자 공통 정보 저장
     * @param commonMemberInfo: 사용자 공통 정보 객체
     * @return 사용자 공통 정보 반환 Member 엔티티 객체
     */
    private Member createMember(SignUpRequest.CommonMemberInfo commonMemberInfo) {
        boolean isExist = memberRepository.existsMemberByEmail(commonMemberInfo.getEmail());
        if(isExist) throw new RuntimeException("이미 존재하는 이메일 계정입니다.");
        Member member = Member.builder()
                .realName(commonMemberInfo.getRealName())
                .email(commonMemberInfo.getEmail())
                .password(passwordEncoder.encode(commonMemberInfo.getPassword()))
                .gender(commonMemberInfo.getGender())
                .build();
        return memberRepository.save(member);
    }

    /**
     * 사용자 권한 할당
     * @param member: 사용자 정보
     * @param authCode: 권한 코드
     *
     */
    private void giveUserRole(Member member,String authCode){
        Role role = roleRepository.findRoleByRoleCode(authCode).orElseThrow(() -> new RuntimeException("유효하지 않은 권한 코드 입니다."));
        UserRole userRole = UserRole.builder().member(member).role(role).build();
        userRoleRepository.save(userRole);
    }


}
