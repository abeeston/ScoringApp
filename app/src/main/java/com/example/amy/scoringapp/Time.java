package com.example.amy.scoringapp;

/**
 * Created by Amy on 6/10/2015.
 */
public class Time {

    private int hour;
    private int min;

    Time() {
        hour = 0;
        min = 0;
    }

    Time(int hour, int min) {
        this.hour = hour;
        this.min = min;
    }

    int getHour() { return hour; }
    int getMin() { return min; }

    void setHour(int hour) { this.hour = hour; }
    void setMin(int min) { this.min = min; }

    boolean compare(Time time2) {

        return this.hour == time2.hour && this.min == time2.min;
    }
    @Override
    public String toString() {
        String amPM = new String();
        if (hour > 11) {
            return "The time is: " + (hour - 12) + ":" + min + " PM";
        }
        else
            return "The time is: " + hour + ":" + min + " AM";
    }
}
