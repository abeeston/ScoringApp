package com.example.amy.scoringapp;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The main screen of the app. Here the user determines which tournament they will add a game to
 * or view the associated games and can log in as an administrator.
 */
public class MainActivity extends ActionBarActivity {

    private List<Tournament> available;      // The list of tournaments to populate the spinner
    private Spinner spinner;                 // Our spinner containing the tournaments
    private Handler handler;                 // For multithreading when loading tournaments

    /**
     * Initialized the variables and calls observeTournament to fill the spinner
     * @param savedInstanceState The saved instance state for loading
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        ActionBar bar = getActionBar();
//        //for color
//        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00C4CD")));

        // Initialize variables
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);
        available = new ArrayList<>();
        handler = new Handler();

        // Get the data from Firebase
        observeTournament();
    }

    /**
     * Fills the spinner with tournaments from the database
     */
    public void fillSpinner() {
        List<String> stringList = new ArrayList<>();

        // Go through all of the tournaments in available
        for (Tournament t : available) {
            stringList.add(t.display());
        }

        // Use the array adapter to change the contents of the spinner
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, stringList);
        spinner.setAdapter(adapter);

        handler.post(new Runnable() {
            @Override
            public void run() {
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

        // Get the selected item and send the tournament's id for updating the game
        int position = spinner.getSelectedItemPosition();
        String id = available.get(position).getID();
        intent.putExtra("TournamentID", id);

        // Get the password and send it for verifying
        String password = available.get(position).getPassword();
        intent.putExtra("Password", password);

        // Start
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

        // Send what's in the spinner in case we want to display it //////////////NOTE: Not completely necessary but we might use it
//        String spinValue = spinner.getSelectedItem().toString();
//        intent.putExtra("Spinner", spinValue);

        // Get the selected item and send the tournament's id for updating the game
        int position = spinner.getSelectedItemPosition();
        String id = available.get(position).getID();
        intent.putExtra("TournamentID", id);

        // Start
        startActivity(intent);
    }

    /**
     * Creates the options menu
     * @param menu The menu
     * @return Boolean
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Returns the item selected
     * @param item The item
     * @return
     */
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
