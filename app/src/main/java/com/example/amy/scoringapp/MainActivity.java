package com.example.amy.scoringapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The main screen of the app. Here the user determines which tournament they will add a game to
 * or view the associated games and can log in as an administrator.
 */
public class MainActivity extends ActionBarActivity {

    private Firebase myFirebaseRef;          // For connecting to Firebase
    private List<Tournament> available;      // The list of tournaments to populate the spinner
    Tournament activeTournament;             // The active tournament
    Spinner spinner;                         // Our spinner containing the tournaments
    private static Context context; ///// NOTE: there was a firebase context?
    private Handler handler = new Handler();
    private ProgressBar progressBar;

    /**
     * Initialized the variables and calls observeTournament to fill the spinner
     * @param savedInstanceState The saved instance state for loading
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Configure our Firebase reference
        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://popping-torch-5466.firebaseio.com/");
        setContentView(R.layout.activity_main);
        MainActivity.context = MainActivity.this.getApplicationContext();
        available = new ArrayList<>();

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        // Get the data from Firebase
        observeTournament();
    }

    /**
     * Fills the spinner with tournaments from the database
     */
    public void fillSpinner() {
        List<String> stringList = new ArrayList<>();

        // Go through all of the tournaments in available
        int count = 0;
        for (Tournament t : available) {
            stringList.add(t.display());
            count++;
        }
        final int countFinal = count;

        // Use the array adapter to change the contents of the spinner
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, stringList); // test will be "available" list
        spinner.setAdapter(adapter);

        handler.post(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < countFinal; i++) {
                    progressBar.setProgress(i * 10);
                }
                progressBar.setProgress(0);
            }
        });
    }

    /**
     * Gets the list of tournaments from the database
     */
    public void observeTournament() {
        Firebase ref = new Firebase("https://scoresubmission.firebaseio.com/Tournaments");
        Query queryRef = ref.orderByKey();

        /**
         * Listens for added children in the database
         */
        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                // Read the data into a map
                Map<String, Object> newPost = (HashMap<String, Object>) dataSnapshot.getValue();

                String id = dataSnapshot.getKey();
                String date = (String) newPost.get("date");
                String location = (String) newPost.get("location");
                String password = (String) newPost.get("password");

                // Create the tournament and add it to the list
                Tournament t = new Tournament(id, date, location, password);
                //MainActivity.this.available.add(t);
                available.add(t);

                // Clear the map between reads
                newPost.clear();

                // Call the method to populate the spinner
                fillSpinner();
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

    /**
     * Brings the user to the SubmitScore login activity and sends the selected tournament
     * @param view For onclick methods
     */
    public void onClickSubmitScore(View view) {
        // Create an intent to put the tournament in and send it to the ScoreSubmit class and activity
        Intent intent = new Intent(this, TournamentPassword.class);

        String spinValue = spinner.getSelectedItem().toString();
        intent.putExtra("Spinner", spinValue);
        startActivity(intent);
    }

    /**
     * Brings the user to the Admin login page
     * @param view For onclick methods
     */
    public void onClickAdmin(View view) {
        Intent intent = new Intent(this, AdminLogin.class);
        startActivity(intent);
    }

    /**
     * Brings the user to the NotificationBoard activity to view past games and passes the active
     * tournament
     * @param view For onclick methods
     */
    public void onClickNotificationBoard(View view) {
        Intent intent = new Intent(this, NotificationBoard.class);

        String spinValue = spinner.getSelectedItem().toString();
        intent.putExtra("Spinner", spinValue);
        //tournament = "Tahoe";
        //intent.putExtra("TournamentName", tournament);
        startActivity(intent);
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
}
