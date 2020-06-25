package com.tomandrieu.utilities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DateUtilsTest {

    @Test
    public void getDate() {
    }

    @Test
    public void getMonth() {
    }

    @Test
    public void getDayFromTimestamp() {
    }

    @Test
    public void sameDay() {
    }

    @Test
    public void sameWeek() {
    }

    @Test
    public void getDiffYears() {
        long now = Long.parseLong("1593120407012");

        long date15_09_1998 = Long.parseLong("905892683835");

        assertEquals(10, DateUtils.getDiffYears(date15_09_1998, now));
    }
}