package com.example.amy.scoringapp;

/**
 * The name and score of a team
 */
public class Team {

    private String name;
    private String score;

    /**
     * Default Constructor
     */
    Team() {
        name = null;
        score = null;
    }

    /**
     * Non-Default Constructor
     * @param name The name
     * @param score The score
     */
    Team(String name, String score) {
        this.name = name;
        this.score = score;
    }

    /** Getters */
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    /** Setters */
    public String getScore() { return score; }
    public void setScore(String score) { this.score = score; }

    public String display() {
        return name + " - " + score;
    }

}
