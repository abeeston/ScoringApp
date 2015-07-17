package com.example.amy.scoringapp;

import com.firebase.client.Firebase;

/**
 * Interface for classes that observes
 * a Firebase database. This meaning
 * that it needs to push and update
 * data and display data from given
 * Firebase
 */
public interface DatabaseObserver {

    /**
     * Updates data in class in given Firebase url
     * @param url Firebase URL
     */
    public void updateData(Firebase url);

    /**
     * Pushes data in class to given Firebase url
     * @param url Firebase url
     */
    public void pushData(Firebase url);

    /**
     * Displays Firebase data in class
     * as a String.
     * @return displayable string
     */
    public String display();
}
