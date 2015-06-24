package com.example.amy.scoringapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private Firebase myFirebaseRef;          // For connecting to Firebase
    private List<Tournament> available;      // The list of tournaments to populate the spinner
    String tournament;                       // The active tournament
    Spinner spinner;                         // Our spinner containing the tournaments

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Configure our Firebase reference
        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://popping-torch-5466.firebaseio.com/");
        setContentView(R.layout.activity_main);

        // Get the data from Firebase
        myFirebaseRef.child("message").setValue("Do you have data? You'll love Firebase.");
        System.out.println("Checkitchekcititehiosadhfoihadsiohf");

        //available = new ArrayList<>();
        List<String> test = new ArrayList<>();
        test.add("Tahoe");
        test.add("Sacramento");
        test.add("Chico");

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, test); // test will be "available" list
        spinner.setAdapter(adapter);

        observeTournament();

        //Tournament tournament = new Tournament("June 5th, 2015", "Chico", "password");
        //tournament.pushData("https://scoresubmission.firebaseio.com/");
    }

    public void observeTournament() {
        Firebase ref = new Firebase("https://scoresubmission.firebaseio.com/Tournaments");
        Query queryRef = ref.orderByKey();

        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                System.out.println(dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onClickReturnScore(){

        return true;
    }

    public boolean onClickNotificationBoard(){

        return true;
    }

    public void onChangeActiveTournament(){

    }

    public void onClickSubmitScore(View view) {
        // Create an intent to put the tournament in and send it to the ScoreSubmit class and activity
        Intent intent = new Intent(this, TournamentPassword.class);

        String spinValue = spinner.getSelectedItem().toString();
        intent.putExtra("Spinner", spinValue);
        tournament = "Tahoe";
        intent.putExtra("TournamentName", tournament);
        startActivity(intent);
    }

    public void onClickAdmin(View view) {
        Intent intent = new Intent(this, AdminLogin.class);
        startActivity(intent);
    }

    public void onClickNotificationBoard(View view) {
        Intent intent = new Intent(this, NotificationBoard.class);

        String spinValue = spinner.getSelectedItem().toString();
        intent.putExtra("Spinner", spinValue);
        tournament = "Tahoe";
        intent.putExtra("TournamentName", tournament);
        startActivity(intent);
    }
}
