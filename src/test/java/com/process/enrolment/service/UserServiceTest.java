package com.process.enrolment.service;

import com.process.enrolment.controller.request.SignUpRequest;
import com.process.enrolment.entity.Member;
import com.process.enrolment.entity.Role;
import com.process.enrolment.entity.UserRole;
import com.process.enrolment.entity.enums.Gender;
import com.process.enrolment.repository.MemberRepository;
import com.process.enrolment.repository.RoleRepository;
import com.process.enrolment.repository.UserRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.UUID;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    private final String studentCode = "ROLECODE_20230405211846941";
    private final String professorCode = "ROLECODE_20230405212111111";
    private final String adminCode = "ROLECODE_20230405212243354";

    @Test
    @DisplayName("관리자 회원가입")
    void signupForAdmin(){
        SignUpRequest.CommonMemberInfo memberInfo
                = SignUpRequest.CommonMemberInfo.builder()
                .realName("이정찬")
                .email("leelee@gmail.com")
                .password("@sD222kdlsl")
                .authorityCode(adminCode)
                .gender(Gender.MAN)
                .build();
        userService.signUpForAdmin(memberInfo);
    }

    @Test
    @DisplayName("학생 회원가입")
    void signupForStudent() {
        String defaultName = "이정찬";
        String emailSuffix = "@naver.com";
        for(int i =0; i<30;i++){
            SignUpRequest.CommonMemberInfo memberInfo
                    = SignUpRequest.CommonMemberInfo.builder()
                    .realName(defaultName + i)
                    .email(UUID.randomUUID().toString() + emailSuffix)
                    .gender(Gender.MAN)
                    .password("#LoveAndPeace1234")
                    .authorityCode(studentCode)
                    .build();
            SignUpRequest.Student student = new SignUpRequest.Student(3, "정보통신공학");
            userService.signupForStudent(memberInfo,student);
        }
    }

    @Test
    void signUpForProfessor() {

        String defaultName = "교수님";
        String emailSuffix = "@naver.com";
        for(int i =0; i<3;i++){
            SignUpRequest.CommonMemberInfo memberInfo
                    = SignUpRequest.CommonMemberInfo.builder()
                    .realName(defaultName + i)
                    .email(UUID.randomUUID().toString() + emailSuffix)
                    .gender(Gender.MAN)
                    .password("#LoveAndPeace1234")
                    .authorityCode(professorCode)
                    .build();
            SignUpRequest.Professor professor = new SignUpRequest.Professor("정보보안", "박사");
            userService.signUpForProfessor(memberInfo,professor);
        }
    }
}