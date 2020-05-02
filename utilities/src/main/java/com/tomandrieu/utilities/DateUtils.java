package com.tomandrieu.utilities;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {
    public static Date getDate(long time) {
        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();//get your local time zone.
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        sdf.setTimeZone(tz);//set time zone.
        String localTime = sdf.format(new Date(time * 1000));
        Date date = new Date();
        try {
            date = sdf.parse(localTime);//get local date
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month];
    }

    public static String getDayFromTimestamp(Calendar calendar) {
        SimpleDateFormat dayFormat = new SimpleDateFormat("E", Locale.getDefault());
        return dayFormat.format(calendar.getTime());
    }
    /**
     * @param cal1
     * @param cal2
     * @return true if the two date are in the same day
     */
    public static boolean sameDay(Calendar cal1, Calendar cal2) {
        return cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
    }

    /**
     * @param cal1 oldest date
     * @param cal2 recent date
     * @return true if the two date are the same week
     */
    public static boolean sameWeek(Calendar cal1, Calendar cal2) {
        return cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
    }

}
