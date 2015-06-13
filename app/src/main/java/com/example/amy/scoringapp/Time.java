package com.example.amy.scoringapp;

/**
 * Created by Mark on 6/13/2015.
 */
public class Time {
    private int hour;
    private int minute;

    public Time() {
        }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    @Override
    public String toString() {
        String amPM = new String();
        if (hour > 11) {
            return "The time is: " + (hour - 12) + ":" + minute + " PM";
        }
        else
            return "The time is: " + hour + ":" + minute + " AM";
    }
}
