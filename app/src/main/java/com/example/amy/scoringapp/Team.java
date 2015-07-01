package com.example.amy.scoringapp;

/**
 * Created by Amy on 6/10/2015.
 */
public class Team {

    private String name;
    private String score;

    Team() {
        name = null;
        score = null;
    }

    Team(String name, String score) {
        this.name = name;
        this.score = score;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getScore() { return score; }
    public void setScore(String score) { this.score = score; }

}
