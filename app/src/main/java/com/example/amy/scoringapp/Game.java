package com.example.amy.scoringapp;

import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains a single game
 */
public class Game implements DatabaseObserver{
    private String tournamentID;   // The tournament id for grouping
    private String courtNum;       // Game court number
    private String location;       // Game location
    private String time;           // Time of the game (entered from the user)
                                   // A Timestamp?
    private String gameID; ///// TODO: Have a second constructor that doesn't need an ID
    private Team team1;            // The first team
    private Team team2;            // The second team

    /**
     * Default constructor; sets all parameters to court 100 at 12:00 PM
     */
    public Game() {
        this.courtNum = "00";
        this.time = "00:00 AM";

        this.gameID = null;
        this.tournamentID = null;
        this.location = null;
    }

    public Game(String tournamentID, String courtNum, String location, String time, String gameID, Team team1, Team team2) {
        this.tournamentID = tournamentID;
        this.courtNum = courtNum;
        this.location = location;
        this.time = time;
        this.gameID = gameID;
        this.team1 = team1;
        this.team2 = team2;
    }

    public Game(String tournamentID, String courtNum, String location, String time, Team team1, Team team2) {
        this.tournamentID = tournamentID;
        this.courtNum = courtNum;
        this.location = location;
        this.time = time;
        this.team1 = team1;
        this.team2 = team2;
        this.gameID = "";
    }

    /** Get court number */
    public String getCourtNum() {
        return courtNum;
    }

    /** Set the court number */
    public void setCourtNum(String courtNum) {
        this.courtNum = courtNum;
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
        url.child("Games/" + tournamentID + "/" + gameID + "/team1").setValue(team1.getName());
        url.child("Games/" + tournamentID + "/" + gameID + "/team2").setValue(team2.getName());
        url.child("Games/" + tournamentID + "/" + gameID + "/score").setValue(team1.getScore() + "-" + team1.getScore());
        url.child("Games/" + tournamentID + "/" + gameID + "/score").setValue(team2.getScore() + "-" + team2.getScore());
        url.child("Games/" + tournamentID + "/" + gameID + "/time").setValue(time);

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
        post.put("team1", team1.getName());
        post.put("team2", team2.getName());
        post.put("score", (team1.getScore() + "-" + team1.getScore()));
        post.put("score", (team2.getScore() + "-" + team2.getScore()));
        post.put("time", time);

        //sets Firebase to match map
        newPostRef.setValue(post);

        //set this ID to Firebase ID
        gameID = newPostRef.getKey();
    }

    @Override
    public String display() {
        if (courtNum == "1") {
            return (time + " " + location + " " + "\n" + team1.display() + "  " + team2.display());
        }
        return (time + " " + location + " " + courtNum + "\n" + team1.display() + "  " + team2.display());
    }
}
