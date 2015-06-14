package com.example.amy.scoringapp;

import java.util.ArrayList;

/**
 * Created by Mark on 6/10/2015.
 */
public class Game implements DatabaseObserver{
    private int courtNum;
    String location;
    protected Time time;
    ArrayList<Team> teams;


    public Game() {
        this.courtNum = 101;
        time.setHour(13);
        time.setMin(30);
    }



    public int getCourtNum() {
        return courtNum;
    }

    public void setCourtNum(int courtNum) {
        this.courtNum = courtNum;
    }

    public void readSubmit() {

    }


    @Override
    public String pullData() {
        return null;
    }

    @Override
    public void pushData() {

    }

    @Override
    public void display() {

    }

    public void sendNotificaion() {

    }
}
