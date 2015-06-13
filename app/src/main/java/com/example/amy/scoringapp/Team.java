package com.example.amy.scoringapp;

/**
 * Created by Amy on 6/10/2015.
 */
public class Team {

    private String name;
    private int score;

    Team() {
        name = null;
        score = 0;
    }

    Team(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

}
