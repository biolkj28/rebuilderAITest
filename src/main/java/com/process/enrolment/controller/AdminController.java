package com.process.enrolment.controller;

import com.process.enrolment.controller.request.AdminRequest;
import com.process.enrolment.controller.request.LectureRequest;
import com.process.enrolment.service.AdminService;
import com.process.enrolment.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final LectureService lectureService;

    /**
     * 권한 생성 
     * @param createRole:권한 등록 객체(권한명, 권한 설명) - 권한명은 영문만 가능 필수 값, 권한 설명은 선택 값
     * @return 상태 코드 200, 권한 등록 문자열 반환
     */
    @PostMapping("/role/create")
    public ResponseEntity<String> createRole(@Valid @RequestBody AdminRequest.CreateRole createRole){
        adminService.createRole(createRole);
        return new ResponseEntity<>("권한 등록", HttpStatus.OK);
    }

    /**
     * 권한 승인
     * @param roleApprove: 권한 승인 대기 중인 사용자 id가 담긴 객체
     * @return 상태 코드 200, 권한 승인 문자열 반환
     */
    @PutMapping("/role/approve")
    public ResponseEntity<String> approveRole(@Valid @RequestBody AdminRequest.RoleApprove roleApprove){
        adminService.approveRole(roleApprove);
        return new ResponseEntity<>("권한 승인", HttpStatus.OK);
    }

    /**
     * 수강 신청 기간 등록
     * @param term: 시작 날짜, 종료 날짜, 수강 신청 개방 여부 속성을 가진 객체
     * @return 상태 코드 200, 수강 신청 기간 등록 성공 문자열 반환
     */
    @PostMapping("/register/term")
    public ResponseEntity<String> createEnrollTerm(@Valid @RequestBody LectureRequest.LectureEnrollTerm term){
        lectureService.createLectureEnrollTerm(term);
        return new ResponseEntity<>("수강 신청 기간 등록 성공", HttpStatus.OK);
    }

    /**
     * 강의 생성
     * @param lecture: 강의 정보를 가진 객체
     * @param userDetails: 인증 인가가 된 사용자 정보
     * @return 상태 코드 200, 강의 등록 문자열 반환
     */
    @PostMapping("/register/lecture")
    public ResponseEntity<String> createEnrollTerm(@Valid @RequestBody LectureRequest.CreateLecture lecture,
                                                   @AuthenticationPrincipal UserDetails userDetails){
        lectureService.createLecture(lecture, userDetails.getUsername());
        return new ResponseEntity<>("강의 등록 성공", HttpStatus.OK);
    }

    /**
     * 수강 신청 오픈(미완성)
     */
    @PutMapping("/lecture/open")
    public ResponseEntity<String> openEnroll(@Valid @RequestBody LectureRequest.CreateLecture lecture){

        return new ResponseEntity<>("수강 신청 열기 성공", HttpStatus.OK);
    }
}
