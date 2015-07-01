package com.example.amy.scoringapp;

import com.firebase.client.Firebase;

import java.util.ArrayList;

/**
 * Contains a single game
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
    public void pullData(Firebase url) {

    }

    @Override
    public void updateData(Firebase url) {

    }

    @Override
    public void pushData(Firebase url) {

    }

    @Override
    public String display() {

        return null;
    }

    public void sendNotificaion() {

    }
}
