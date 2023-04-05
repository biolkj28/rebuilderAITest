package com.process.enrolment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.process.enrolment.controller.request.AdminRequest;
import com.process.enrolment.controller.request.LectureRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerTest {

    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper objectMapper;


    @Test
    @DisplayName("권한 등록")
    void createRole() throws Exception {
        //given
        AdminRequest.CreateRole role = new AdminRequest.CreateRole("admin", "");
        String body = objectMapper.writeValueAsString(role);

        //then
        mvc.perform(post("/admin/role/create")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON) //보내는 데이터의 타입을 명시)
        )
                .andExpect(status().isOk());
    }

    @Test
    void approveRole() {
    }

    @Test
    void createLectureEnrollTerm() throws Exception {
        LocalDateTime start = LocalDateTime.of(2023, 5, 1, 0, 0, 0);
        LocalDateTime end = LocalDateTime.of(2023, 5, 28, 0, 0, 0);
        LectureRequest.LectureEnrollTerm term = new LectureRequest.LectureEnrollTerm(start, end);
        String body = objectMapper.writeValueAsString(term);

        mvc.perform(post("/admin/term/register")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON) //보내는 데이터의 타입을 명시)
        ).andExpect(status().isOk());
    }
}