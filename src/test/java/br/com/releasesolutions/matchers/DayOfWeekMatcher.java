package br.com.releasesolutions.matchers;

import br.com.releasesolutions.utils.DateUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DayOfWeekMatcher extends TypeSafeMatcher<Date> {

    private final Integer dayOfWeek;

    public DayOfWeekMatcher(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public void describeTo(Description description) {

        Calendar date = Calendar.getInstance();
        date.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        String dateDescription = date.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, new Locale("pt", "BR"));
        description.appendText(dateDescription);
    }

    @Override
    protected boolean matchesSafely(Date date) {

        return DateUtils.checkDayOfWeek(date, dayOfWeek);
    }
}
