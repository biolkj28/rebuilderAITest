package com.process.enrolment.controller.request;

import com.process.enrolment.entity.enums.Gender;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class SignUpRequest {

    // 회원가입 공통 정보
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CommonMemberInfo{
        @NotBlank(message = "성명은 필수 입력 값입니다.")
        private String realName;

        @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
        @NotBlank(message = "이메일은 필수 입력 값입니다.")
        private String email;

        @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
        private String password;

        @NotBlank(message = "성별은 필수 입력 값입니다.")
        private Gender gender;

        @NotBlank(message = "권한 코드 필수 선택 값입니다.")
        private String authorityCode;

        @Builder
        private CommonMemberInfo(String realName, String email, String password, Gender gender, String authorityCode) {
            this.realName = realName;
            this.email = email;
            this.password = password;
            this.gender = gender;
            this.authorityCode = authorityCode;
        }
    }
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    //학생 정보
    public static class Student{
        @NotBlank(message = "학년 정보는 필수 입력 값입니다.")
        private int grade;
        @NotBlank(message = "전공 정보는 필수 입력 값입니다.")
        private String major;

        public Student(int grade, String major) {
            this.grade = grade;
            this.major = major;
        }
    }
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    // 교수정보
    public static class Professor{

        @NotBlank(message = "담당 전공 정보는 필수 입력 값입니다.")
        private String departure;

        @NotBlank(message = "직책 정보는 필수 입력 값입니다.")
        private String position;

        public Professor(String departure, String position) {
            this.departure = departure;
            this.position = position;
        }
    }
}
