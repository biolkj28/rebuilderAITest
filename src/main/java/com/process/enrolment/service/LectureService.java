package com.process.enrolment.service;

import com.process.enrolment.controller.request.LectureEnrollOpenRequest;
import com.process.enrolment.controller.request.LectureRequest;
import com.process.enrolment.entity.Lecture;
import com.process.enrolment.entity.LectureEnrollTerm;
import com.process.enrolment.entity.Member;
import com.process.enrolment.entity.Professor;
import com.process.enrolment.entity.enums.DayOfWeeksKor;
import com.process.enrolment.repository.LectureEnrollTermRepository;
import com.process.enrolment.repository.LectureRepository;
import com.process.enrolment.repository.MemberRepository;
import com.process.enrolment.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final MemberRepository memberRepository;
    private final LectureEnrollTermRepository lectureEnrollTermRepository;
    private final ProfessorRepository professorRepository;
    private final LectureRepository lectureRepository;



    /**
     * 수강 신청 기간 등록
     * @param term : 수강 신청 기간을 담은 객체
     * @apiNote : 수강 신청 기간 DB 존재 여부 확인 후 없으면 저장
     */
    @Transactional
    public void createLectureEnrollTerm(LectureRequest.LectureEnrollTerm term) {
        if (lectureEnrollTermRepository.isExists(term.getEnrollStartDate(), term.getEnrollEndDate()) > 0)
            throw new RuntimeException("이미 해당 날짜의 수강 신청 기간이 존재 합니다.");

        LectureEnrollTerm lectureEnrollTerm = LectureEnrollTerm.builder()
                .enrollStartDate(term.getEnrollStartDate())
                .enrollEndDate(term.getEnrollEndDate())
                .build();

        lectureEnrollTermRepository.save(lectureEnrollTerm);
    }

    // 강의 등록
    @Transactional
    public void createLecture(LectureRequest.CreateLecture lectureInfo, String adminEmail) {

        DayOfWeeksKor dayOfWeeksKor = DayOfWeeksKor.fromValue(lectureInfo.getDayOfWeeksKor());

        Member register = memberRepository
                .findMemberByEmail(adminEmail)
                .orElseThrow(() -> new IllegalStateException("잘못 된 계정 입니다."));

        Professor professor = professorRepository
                .findById(lectureInfo.getProfessor_id())
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 교수 입니다."));

        //강의는 최대 2개 확인
        int lectureCount = lectureRepository.countLectureByProfessor(professor);
        if (lectureCount > 2) throw new IllegalStateException("교수 당 강의 수는 최대 2개 입니다.");

        //같은 요일 강의 등록 시, 시간 대가 겹치는 지 확인
        Lecture sameDayLecture = lectureRepository.findLectureByProfessorAndDayOfWeeks(dayOfWeeksKor, professor)
                .orElse(null);
        if (sameDayLecture != null) {
            LocalTime endTime = sameDayLecture.getEndTime();
            LocalTime startTime = sameDayLecture.getStartTime();

            if (startTime.isAfter(lectureInfo.getEndTime()) ||
                    endTime.isBefore(lectureInfo.getStartTime()) ||
                    startTime.equals(lectureInfo.getStartTime()) ||
                    endTime.equals(lectureInfo.getEndTime())
            ) throw new IllegalStateException("기존 수업 시간과 겹칩니다.");
        }

        LectureEnrollTerm lectureEnrollTerm = lectureEnrollTermRepository
                .findById(lectureInfo.getTerm_id())
                .orElseThrow(() -> new IllegalStateException("잘못된 수강신청 기간입니다."));

        Lecture lecture = Lecture.builder()
                .name(lectureInfo.getName())
                .description(lectureInfo.getDesc())
                .dayOfWeeks(dayOfWeeksKor)
                .maxPeople(lectureInfo.getMax())
                .minPeople(lectureInfo.getMin())
                .startTime(lectureInfo.getStartTime())
                .endTime(lectureInfo.getEndTime())
                .professor(professor)
                .term(lectureEnrollTerm)
                .register(register)
                .build();

        lectureRepository.save(lecture);

    }

    @Transactional
    public void openEnroll(LectureEnrollOpenRequest lectureEnrollOpenRequest) {
        Iterable<Professor> all = professorRepository.findAll();
        Long lectureEnrollTerm_id = lectureEnrollOpenRequest.getLectureEnrollTerm_id();

    }
}
