package br.com.releasesolutions.utils;

import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

public class DateUtils {

    /**
     * Returns the date sent by parameter with the addition of the desired days
     *      the Date can be in the future (days > 0) or in the past (days < 0).
     *
     * @param date
     * @param days
     * @return
     */

    public static Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(DAY_OF_MONTH, days);

        return calendar.getTime();
    }

    /**
     * Returns the current date with the difference of days sent per parameter
     *      the Date can be in the future (positive parameter) or in the past (negative parameter).
     * @param
     * @return
     */

    public static Date getDateWithDaysDifference(int days) {

        return addDays(new Date(), days);
    }

    /**
     * Returns an instance of <code>Date</code> reflecting the values passed by parameter.
     * @param day
     * @param month
     * @param year
     * @return
     */

    public static Date getDate(int day, int month, int year) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(DAY_OF_MONTH, day);
        calendar.set(MONTH, month - 1);
        calendar.set(YEAR, year);

        return calendar.getTime();
    }

    /**
     * Checks if one date is the same as another. This comparison considers only day, month and year.
     * @param date1
     * @param date2
     */

    public static boolean isSameDate(Date date1, Date date2) {

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        return calendar1.get(DAY_OF_MONTH) == calendar2.get(DAY_OF_MONTH)
                && calendar1.get(MONTH) == calendar2.get(MONTH)
                && calendar1.get(YEAR) == calendar2.get(YEAR);
    }

    /**
     * @param
     * @param
     * @return
     */

    public static boolean checkDayOfWeek(Date date, int dayOfWeek) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.DAY_OF_WEEK) == dayOfWeek;
    }
}
