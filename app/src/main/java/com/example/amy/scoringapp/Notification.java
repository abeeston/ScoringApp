package com.example.amy.scoringapp;

import com.firebase.client.Firebase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Mark on 6/14/2015.
 */
public class Notification implements DatabaseObserver{
    public Notification(String submitter, Game content) {
        this.submitter = submitter;
        this.timeStamp = new SimpleDateFormat("HHmm").format(Calendar.getInstance().getTime());
        this.content = content;
    }

    private String submitter;
    private String timeStamp;
    private Game content;

    /**
     * Pulls
     * @param url
     */
    @Override
    public void pullData(Firebase url) {

    }

    @Override
    public void updateData(Firebase url) {

    }

    @Override
    public void pushData(Firebase url) {

    }

    public String display(){

        return null;
    }
}
