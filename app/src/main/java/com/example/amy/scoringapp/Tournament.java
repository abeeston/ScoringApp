package com.example.amy.scoringapp;

import java.util.Date;
import java.util.List;

/**
 * Created by Trevor on 6/13/2015.
 */
public class Tournament implements DatabaseObserver {
    public Tournament() {
    }

    public Tournament(Date date, String city) {
        this.date = date;
        this.city = city;
    }

    private String city;
    private Date date;
    private int ID;
    private List<String> games;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
    public void pushData() {

    }

    @Override
    public void display() {

    }

}
