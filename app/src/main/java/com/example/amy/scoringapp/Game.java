package com.example.amy.scoringapp;

import com.firebase.client.Firebase;

import java.util.ArrayList;

/**
 * Contains a single game
 * Created by Mark on 6/10/2015.
 */
public class Game implements DatabaseObserver{
    private int courtNum;
    String location;
    protected Time time;
    ArrayList<Team> teams;

    /**
     * Default constructor; sets all parameters to court 100 at 12:00 PM
     */
    public Game() {
        this.courtNum = 100;
        time.setHour(12);
        time.setMin(00);
    }

    /** Get court number */
    public int getCourtNum() {
        return courtNum;
    }

    /** Set the court number */
    public void setCourtNum(int courtNum) {
        this.courtNum = courtNum;
    }

    /**Submit the score and date */
     public void readSubmit() {

    }

    /**
     * Pull the data from Firebase
     * @param url Firebase URL
     */
    @Override
    public void pullData(Firebase url) {

    }

    /**
     * Updates the data in Firebase.
     * @param url Firebase URL
     */
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
