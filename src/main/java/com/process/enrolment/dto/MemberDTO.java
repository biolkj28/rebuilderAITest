package com.process.enrolment.dto;

import lombok.Getter;

@Getter
public class MemberDTO {
    private String email;
    private String password;

    public MemberDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
