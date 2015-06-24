package com.example.amy.scoringapp;

import android.test.InstrumentationTestCase;
import android.util.Log;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Amy on 6/10/2015.
 */
public class TimeTest extends InstrumentationTestCase{

    private static final String TAG_TEST = "TimeTest";
    public void testHour() {
        Time time = new Time();
        int temp = 5;

        Log.v(TAG_TEST, "You are at: " + time.getMin() + " minutes!");
        Log.v(TAG_TEST, "You are at: " + time.getHour() + " hours!");

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
