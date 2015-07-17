package com.example.amy.scoringapp;

import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains a single game with teams
 * and their scores and where the game
 * was played. Data in class can be pushed
 * to or used to edit a Firebase database.
 * Uses the constructor for data pulled.
 * Does not pull it for you.
 */
public class Game implements DatabaseObserver{

    private String tournamentID;   // The tournament id for grouping
    private String courtNum;       // Game court number
    private String location;       // Game location
    private String time;           // Time of the game (entered from the user)
    private String gameID;         // ID where game is stored in Firebase
    private Team team1;            // The first team
    private Team team2;            // The second team

    /**
     * Default constructor; sets all parameters to court 00 at 00:00 AM
     */
    public Game() {
        this.courtNum = "00";
        this.time = "00:00 AM";

        this.gameID = null;
        this.tournamentID = null;
        this.location = null;
    }

    /**
     * Non Default constuctor for data already
     * matched to and pulled from a Firebase.
     * @param tournamentID ID for Firebase
     * @param courtNum Gym Court number
     * @param location Gym location
     * @param time Time game started
     * @param gameID ID for Firebase
     * @param team1 Home team
     * @param team2 Away team
     */
    public Game(String tournamentID, String courtNum, String location, String time, String gameID, Team team1, Team team2) {
        this.tournamentID = tournamentID;
        this.courtNum = courtNum;
        this.location = location;
        this.time = time;
        this.gameID = gameID;
        this.team1 = team1;
        this.team2 = team2;
    }

    /**
     * Non Default constructor for data that doesn't
     * Have a Firebase reference yet.
     * @param tournamentID ID for Firebase
     * @param courtNum Gym Court Number
     * @param location Gym location
     * @param time time game started
     * @param team1 Home team
     * @param team2 Away team
     */
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
        url.child("Games/" + tournamentID + "/" + gameID + "/score1").setValue(team1.getScore());
        url.child("Games/" + tournamentID + "/" + gameID + "/score2").setValue(team2.getScore());
        url.child("Games/" + tournamentID + "/" + gameID + "/time").setValue(time);

    }

    /**
     * Pushes the data from a game class to the Firebase table
     * @param url the Firebase url to access
     */
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
        post.put("score1", team1.getScore());
        post.put("score2", team2.getScore());
        post.put("time", time);

        //sets Firebase to match map
        newPostRef.setValue(post);

        //set this ID to Firebase ID
        gameID = newPostRef.getKey();
    }

    /**
     * Displays a game formatted for a notification
     * @return Displayable String
     */
    @Override
    public String display() {
        String text = null;
        if (courtNum == "1") {
            text = ("\n\t" + time + " " + location + " " + "\t\n" + team1.display() + "  " + team2.display());
        } else {
            text = (time + " " + location + " Court: " + courtNum + "\n" + team1.display() + "  " + team2.display());
            //text.setText(text.replace("\\n", "\n"));
        }
        return text;
    }

    public String display1() {
        if (courtNum == "1") {
            return (time + " " + location);
        }
        return (time + " " + location + " Court: " + courtNum);
    }

    public String display2() {
        return team1.display() + "  " + team2.display();
    }
}
