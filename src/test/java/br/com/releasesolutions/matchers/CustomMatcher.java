package br.com.releasesolutions.matchers;

import java.util.Calendar;

public class CustomMatcher {

    public static DayOfWeekMatcher is(Integer dayOfWeek) {

        return new DayOfWeekMatcher(dayOfWeek);
    }

    public static DayOfWeekMatcher isMonday() {

        return new DayOfWeekMatcher(Calendar.MONDAY);
    }
}
