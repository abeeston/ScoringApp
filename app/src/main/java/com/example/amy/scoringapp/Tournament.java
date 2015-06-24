package com.example.amy.scoringapp;

import com.firebase.client.Firebase;

import java.util.Date;
import java.util.List;

/**
 * Created by Trevor on 6/13/2015.
 */
public class Tournament implements DatabaseObserver {
    public Tournament() {
    }

    public Tournament(String date, String city, String password) {
        ID = 1;
        this.date = date;
        this.city = city;
        this.password = password;
        this.games = null;
    }

    private String city;
    private String date;
    private int ID;
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
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
    public void display() {

    }

}
