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
    /**
     * default constructor
     */
    public Tournament() {
        this.date = null;
        this.city = null;
        this.password = null;
        this.games = null;
    }

    /**
     * Non default constructor. Games must be added after creation.
     * @param date Starting date of tournament
     * @param city Tournament city
     * @param password password needed to alter tournament
     */
    public Tournament(String date, String city, String password) {
        this.date = date;
        this.city = city;
        this.password = password;
        this.games = null;
    }

    /**
     * Non default constructor for loading from the database
     * @param ID Unique ID to be used in Firebase
     * @param date Starting date of tournament
     * @param city Tournament city
     * @param password password needed to alter tournament
     */
    public Tournament(String ID, String date, String city, String password) {
        this.ID = ID;
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
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getID() { return ID; }
    public void setID(String ID) { this.ID = ID; }
    public List<String> getGames() { return games; }
    public void setGames(List<String> games) { this.games = games; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    /**
     * update Firebase with data from class
     * @param url url to the Firebase table
     */
    @Override
    public void updateData(Firebase url) {
        //change values in Firebase to current values in this class
        url.child("Tournaments/" + ID + "/date").setValue(date);
        url.child("Tournaments/" + ID + "/location").setValue(city);
        url.child("Tournaments/" + ID + "/password").setValue(password);
    }

    /**
     * Create New Firebase table slot and add class data
     * @param url url to the Firebase table
     */
    @Override
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

    /**
     * Create new Firebase table slot and add class data
     * @return displayable string with city and date
     */
    @Override
    public String display() {
        return city + "   " + date;
    }

}
