package com.process.enrolment.controller.request;


import com.process.enrolment.entity.enums.DayOfWeeksKor;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;

public class LectureRequest {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class LectureEnrollTerm {
        @Future(message = "현재 날짜 이후를 선택해주세요.")
        private LocalDateTime enrollStartDate;


        @Future(message = "현재 날짜 이후를 선택해주세요.")
        private LocalDateTime enrollEndDate;

        public LectureEnrollTerm(LocalDateTime enrollStartDate, LocalDateTime enrollEndDate) {

            int dayCheck = enrollStartDate.compareTo(enrollEndDate);
            if (dayCheck == 0) throw new IllegalStateException("시작 날짜와 종료 날짜가 같습니다.");
            else if (dayCheck > 0) throw new IllegalStateException("종료 날짜 보다 이전 날짜를 입력해주세요.");

            Period between = Period.between(enrollStartDate.toLocalDate(), enrollEndDate.toLocalDate());
            if (between.getDays() > 28 || between.getDays() < 14)
                throw new RuntimeException("수강 신청 기간은 2주 ~ 4주 사이여야합니다.");


            this.enrollStartDate = enrollStartDate;
            this.enrollEndDate = enrollEndDate;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CreateLecture {

        @NotBlank(message = "강의 명을 입력해주세요.")
        private String name;
        @NotBlank(message = "강의 설명을 입력해주세요.")
        private String desc;

        @NotBlank(message = "요일을 선택 해주세요.")
        private String dayOfWeeksKor;

        private LocalTime startTime;
        private LocalTime endTime;

        @Max(value = 15, message = "최대 15명 까지 가능 합니다.")
        @Min(value = 6, message = "최소 6명 까지 가능 합니다.")
        private int max;

        @Max(value = 15, message = "최대 15명 까지 가능 합니다.")
        @Min(value = 6, message = "최소 6명 까지 가능 합니다.")
        private int min;

        @NotNull(message = "수강 신청 기간을 선택해주세요.")
        private Long term_id;
        @NotNull(message = "교수를 선택해주세요.")
        private Long professor_id;

        @Builder
        private CreateLecture(String name, String desc, String dayOfWeeksKor, LocalTime startTime, LocalTime endTime, int max, int min, Long term_id, Long professor_id) {
            this.name = name;
            this.desc = desc;
            this.dayOfWeeksKor = dayOfWeeksKor;
            this.startTime = startTime;
            this.endTime = endTime;
            this.max = max;
            this.min = min;
            this.term_id = term_id;
            this.professor_id = professor_id;

            if (max < min) throw new IllegalStateException("최대 인원이 최소인원보다 작습니다.");
            else if (max == min) throw new IllegalStateException("최대 인원과 최소인원보다 같습니다.");

            if (endTime.isBefore(startTime)) {
                throw new IllegalStateException("종료 시간이 시작 시간 보다 빠릅니다.");
            } else if (startTime.equals(endTime)) {
                throw new IllegalStateException("시작 시간과 종료 시간이 같습니다.)");
            }
        }
    }


}
