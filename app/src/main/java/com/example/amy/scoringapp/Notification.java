package com.example.amy.scoringapp;

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

    @Override
    public String pullData() {
        return null;
    }

    @Override
    public void pushData(String url) {

    }

    public String display(){

        return null;
    }
}
