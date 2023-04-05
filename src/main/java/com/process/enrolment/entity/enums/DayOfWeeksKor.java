package com.process.enrolment.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DayOfWeeksKor {

    MON("월"), TUE("화"), WED("수"), THU("목"), FRI("금");

    private final String dayOfWeeks;


    public static DayOfWeeksKor fromValue(String value) {
        System.out.println(value);
        switch (value) {
            case "MON":
                return DayOfWeeksKor.MON;
            case "TUE":
                return DayOfWeeksKor.TUE;
            case "WED":
                return DayOfWeeksKor.WED;
            case "THU":
                return DayOfWeeksKor.THU;
            case "FRI":
                return DayOfWeeksKor.FRI;
        }
        return null;
    }
}
