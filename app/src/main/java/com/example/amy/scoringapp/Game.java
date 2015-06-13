package com.example.amy.scoringapp;

import java.util.ArrayList;

/**
 * Created by Mark on 6/10/2015.
 */
public class Game {
    private int courtNum;
    String location;
    protected Time time;


    public Game() {
        this.courtNum = 101;
        time.setHour(13);
        time.setMinute(30);
    }



    public int getCourtNum() {
        return courtNum;
    }

    public void setCourtNum(int courtNum) {
        this.courtNum = courtNum;
    }
}
