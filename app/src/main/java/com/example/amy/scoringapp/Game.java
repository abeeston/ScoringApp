package com.example.amy.scoringapp;

import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Contains a single game
 */
public class Game implements DatabaseObserver{
    private String courtNum;
    private String location;
    protected Time time;
    private String tournamentID;
    private String gameID;
    private ArrayList<Team> teams;

    /**
     * Default constructor; sets all parameters to court 100 at 12:00 PM
     */
    public Game() {
        this.courtNum = "00";
        this.time.setHour("00");
        this.time.setMin("00");
        this.teams = null;
        this.gameID = null;
        this.tournamentID = null;
        this.location = null;
    }

    /** Get court number */
    public String getCourtNum() {
        return courtNum;
    }

    /** Set the court number */
    public void setCourtNum(String courtNum) {
        this.courtNum = courtNum;
    }

    /**Submit the score and date */
     public void readSubmit() {

    }

    /**
     * Pull the data from Firebase
     * @param url Firebase URL
     */
    @Override
    public void pullData(Firebase url) {

    }

    /**
     * Updates the data in Firebase.
     * @param url Firebase URL
     */
    @Override
    public void updateData(Firebase url) {
        //change values in Firebase to current values in this class
        url.child("Games/" + tournamentID + "/" + gameID + "/court").setValue(courtNum);
        url.child("Games/" + tournamentID + "/" + gameID + "/location").setValue(location);
        url.child("Games/" + tournamentID + "/" + gameID + "/team1").setValue(teams.get(0).getName());
        url.child("Games/" + tournamentID + "/" + gameID + "/team2").setValue(teams.get(1).getName());
        url.child("Games/" + tournamentID + "/" + gameID + "/score").setValue(teams.get(0).getScore() + "-" + teams.get(1).getScore());
        url.child("Games/" + tournamentID + "/" + gameID + "/time").setValue(time.MilitaryTime());

    }

    @Override
    public void pushData(Firebase url) {
        //creates reference to games in tournament
        Firebase ref = url.child("Games");
        ref = ref.child(tournamentID);

        //makes new spot with unique ID in games list in current tournament
        Firebase newPostRef = ref.push();

        //create and push variables to hash map
        Map<String, String> post = new HashMap<>();
        post.put("court",courtNum);
        post.put("location", location);
        post.put("team1", teams.get(0).getName());
        post.put("team2", teams.get(1).getName());
        post.put("score", (teams.get(0).getScore() + "-" + teams.get(1).getScore()));
        post.put("time", time.MilitaryTime());

                //sets Firebase to match map
        newPostRef.setValue(post);

        //set this ID to Firebase ID
        gameID = newPostRef.getKey();
    }

    @Override
    public String display() {

        return null;
    }

    public void sendNotificaion() {

    }
}
