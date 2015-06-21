package com.example.amy.scoringapp;

import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Mark on 6/14/2015.
 */
public class CreateTournament {
    Map<String, String> toPush;

    public void onCreate(){



    }

    public void pushInformation(){

    }


    public void onSubmit(){
        Firebase ref = new Firebase("https://scoresubmission.firebaseio.com/");

        ref.child("Tournaments/01/date").setValue("02/18/2020");
        ref.child("Tournaments/01/location").setValue("Guantanamo Bay, Cuba");
        ref.child("Tournaments/01/password").setValue("OrangeHippo");

    }
}
