package br.com.releasesolutions.matchers;

import br.com.releasesolutions.utils.DateUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Date;

public class DifferentialDateDaysMatcher extends TypeSafeMatcher<Date> {

    private final Integer qttDays;

    public DifferentialDateDaysMatcher(Integer qttDays) {
        this.qttDays = qttDays;
    }
    
    @Override
    public void describeTo(Description description) {

    }

    @Override
    protected boolean matchesSafely(Date date) {

        return DateUtils.isSameDate(date, DateUtils.getDateWithDaysDifference(qttDays));
    }
}
