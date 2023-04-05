package com.process.enrolment.service;

import com.process.enrolment.controller.request.LectureRequest;
import com.process.enrolment.entity.enums.DayOfWeeksKor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class LectureServiceTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    LectureService lectureService;


    @Test
    void lectureRegister() {
//        String[] title = {"데이터베이스","통신","정보 보안","전자 회로",""}
//
//        LectureRequest.CreateLecture lectureInfo = LectureRequest.CreateLecture.builder()
//                .name("데이터베이스 개론")
//                .desc("데이베이스의 기초와 이해")
//                .dayOfWeeksKor(DayOfWeeksKor.TUE)
//                .startTime(LocalTime.of(9, 0))
//                .endTime(LocalTime.of(10, 0))
//                .build();
//
//
//        lectureService.createLecture();
    }
}