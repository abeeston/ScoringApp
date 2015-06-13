package com.example.amy.scoringapp;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Amy on 6/10/2015.
 */
public class TimeTest {

    public void testHour() {
        Time time = new Time();
        int temp = 5;

        assertEquals(temp, time.getHour());

        time.setHour(temp);
        assertEquals(temp, time.getHour());
    }

    public void testMin() {
        Time time = new Time();
        int temp = 30;

        assertEquals(temp, time.getMin());

        time.setMin(temp);
        assertEquals(temp, time.getMin());
    }
}
