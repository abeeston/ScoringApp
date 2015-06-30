package com.example.amy.scoringapp;

import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Tournament Class
 * Stores the information needed to identify a tournament.
 * Implements DatabaseObserve so that it can communicate with
 * a Firebase account that has a tournament table in order to
 * push data and keep data constant between this and Firebase.
 */
public class Tournament implements DatabaseObserver {
    //default constructor
    public Tournament() {
        this.date = null;
        this.city = null;
        this.password = null;
        this.games = null;
    }

    //Non default constructor. Games must be added after creation.
    public Tournament(String date, String city, String password) {
        this.date = date;
        this.city = city;
        this.password = password;
        this.games = null;
    }

    //private variables
    private String city;
    private String date;
    private String ID;
    private List<String> games;
    private String password;

    //All standard getters and setters
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public List<String> getGames() {
        return games;
    }
    public void setGames(List<String> games) { this.games = games; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    @Override
    //Update class with data from firebase
    public void pullData(Firebase url) {
        //we may or may not need this class
        //I need to understand more how Marks
        //load tournament class works.
    }

    @Override
    //update Firebase with data from class
    public void updateData(Firebase url) {
        //creates reference to Tournments
        Firebase ref = url.child("Tournaments");

        //change values in Firebase to current values in this class
        ref.child("Tournaments/" + ID + "/date").setValue(date);
        ref.child("Tournaments/" + ID + "/location").setValue(city);
        ref.child("Tournaments/" + ID + "/password").setValue(password);
    }

    @Override
    //Create New Firebase table slot and add class data
    public void pushData(Firebase url) {
        //creates reference to Tournaments
        Firebase ref = url.child("Tournaments");

        //makes new spot with unique ID in tournaments list
        Firebase newPostRef = ref.push();

        //create and push variables to hash map
        Map<String, String> post = new HashMap<>();
        post.put("date",date);
        post.put("location", city);
        post.put("password", password);

        //sets Firebase to match map
        newPostRef.setValue(post);

        //set this ID to Firebase ID
        ID = newPostRef.getKey();
    }

    @Override
    public String display() {
        //return "Test!";
        return city + "   " + date;
    }

}
