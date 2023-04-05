package com.process.enrolment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.process.enrolment.dto.MemberDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    MockMvc mvc;
    ObjectMapper objectMapper;

    @BeforeEach
    void setting() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("로그인")
    void auth() throws Exception {

        MemberDTO memberDTO = new MemberDTO("27a36a25-f288-46ef-a624-91982eb74274@naver.com", "#LoveAndPeace1234");
        String body = objectMapper.writeValueAsString(memberDTO);
        mvc.perform(post("/auth")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON) //보내는 데이터의 타입을 명시)
                )
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("로그인 실패")
    void auth_fail() throws Exception {

        MemberDTO memberDTO = new MemberDTO("sm@naver.com", "#LoveAndPeace1234");
        String body = objectMapper.writeValueAsString(memberDTO);
        mvc.perform(post("/auth")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON) //보내는 데이터의 타입을 명시)
                )
                .andReturn().getResponse().getContentAsString();
    }
}