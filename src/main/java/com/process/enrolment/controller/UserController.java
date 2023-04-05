package com.process.enrolment.controller;

import com.process.enrolment.controller.request.SignUpRequest;
import com.process.enrolment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 회원가입
     * @param commonMemberInfo: 공통 사용자 정보, 각 속성에 맞는 유효성 검증 request 객체에 포함
     * @param studentInfo: 학생 정보
     * @param professorInfo: 교수 정보
     * @return 상태코드 200
     */
    @PostMapping("/signup")
    public ResponseEntity<String> signup(
            @Valid @RequestBody SignUpRequest.CommonMemberInfo commonMemberInfo,
            @Valid @RequestBody(required = false) SignUpRequest.Student studentInfo,
            @Valid @RequestBody(required = false) SignUpRequest.Professor professorInfo
    ) {
        if(studentInfo!=null){
            userService.signupForStudent(commonMemberInfo, studentInfo);
        }

        if(professorInfo!=null){
            userService.signUpForProfessor(commonMemberInfo, professorInfo);
        }

        return new ResponseEntity<>("회원가입 성공", HttpStatus.OK);
    }
}
