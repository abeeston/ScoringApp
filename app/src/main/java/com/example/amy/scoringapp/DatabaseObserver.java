package com.example.amy.scoringapp;

import com.firebase.client.Firebase;

/**
 * Created by Trevor on 6/13/2015.
 */
public interface DatabaseObserver {

    public void pullData(Firebase url);
    public void updateData(Firebase url);
    public void pushData(Firebase url);
    public String display();
}
