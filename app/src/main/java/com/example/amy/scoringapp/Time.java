package com.example.amy.scoringapp;

/**
 * Created by Amy on 6/10/2015.
 */
public class Time {

    private String hour;
    private String min;

    Time() {
        hour = "00";
        min = "00";
    }

    Time(String hour, String min, String amPm) {
        if(amPm == "pm") {
            this.hour = Integer.toString(Integer.parseInt(hour) + 12);
        }
        else {
            this.hour = hour;
        }
        this.min = min;
    }

    String getHour() { return hour; }
    String getMin() { return min; }

    void setHour(String hour) { this.hour = hour; }
    void setMin(String min) { this.min = min; }

    boolean compare(Time time2) {

        return this.hour == time2.hour && this.min == time2.min;
    }

    public String StandardTime() {
        if((Integer.parseInt(hour)) > 12) {
            return (Integer.toString(Integer.parseInt(hour) - 12)) + ":" + min + " pm";
        }

        return hour + ":" + min + " am";
    }

    public String MilitaryTime() {
        return hour + min;
    }
    /*
    @Override
    public String toString() {
        String amPM = new String();
        if (hour > 11) {
            return "The time is: " + (hour - 12) + ":" + min + " PM";
        }
        else
            return "The time is: " + hour + ":" + min + " AM";
    }*/
}
