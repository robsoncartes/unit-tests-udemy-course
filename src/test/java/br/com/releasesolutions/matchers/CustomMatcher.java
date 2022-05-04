package br.com.releasesolutions.matchers;

import java.util.Calendar;

public class CustomMatcher {

    public static DayOfWeekMatcher is(Integer dayOfWeek) {

        return new DayOfWeekMatcher(dayOfWeek);
    }

    public static DayOfWeekMatcher isMonday() {

        return new DayOfWeekMatcher(Calendar.MONDAY);
    }

    public static DifferentialDateDaysMatcher todayWithDaysOfDifference(Integer qttDays) {

        return new DifferentialDateDaysMatcher(qttDays);
    }

    public static DifferentialDateDaysMatcher today() {

        return new DifferentialDateDaysMatcher(0);
    }

    public static DifferentialDateDaysMatcher tomorrow(){

        return new DifferentialDateDaysMatcher(1);
    }
}
