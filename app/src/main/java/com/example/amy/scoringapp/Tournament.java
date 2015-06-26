package com.example.amy.scoringapp;

import com.firebase.client.Firebase;

import java.util.List;

/**
 * Created by Trevor on 6/13/2015.
 */
public class Tournament implements DatabaseObserver {
    public Tournament() {
    }

    public Tournament(String id, String date, String city, String password) {
        ID = id;
        this.date = date;
        this.city = city;
        this.password = password;
        this.games = null;
    }

    private String city;
    private String date;
    private String ID;
    private List<String> games;
    private String password;

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

    public void setGames(List<String> games) {
        this.games = games;
    }

    @Override
    public String pullData() {
        return null;
    }

    @Override
    public void pushData(String url) {
        //Firebase ref = new Firebase("https://scoresubmission.firebaseio.com/");
        Firebase ref = new Firebase(url);


        ref.child("Tournaments/" + ID + "/date").setValue(date);
        ref.child("Tournaments/" + ID + "/location").setValue(city);
        ref.child("Tournaments/" + ID + "/password").setValue(password);
    }

    @Override
    public String display() {
        //return "Test!";
        return city + "   " + date;
    }

    @Override
    public String toString() {
        return "ToSTRING!";
    }
}
