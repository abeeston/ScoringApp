package com.example.amy.scoringapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

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
 * This class encapsulates the functions related to loading the games associated with a given
 * tournament and displaying them in a list view.
 */
public class NotificationBoard extends ActionBarActivity {

    private List<Game> games;        // The list of games to be gained from the database
    private Handler handler;         // For updating the progress bar
    private String tournID;          // To load games for only that tournament
    private LinearLayout layout;     // The layout that will be added to

    /**
     * Gets the selected tournament from the previous page and initializes variables
     * @param savedInstanceState The saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_board);

        Intent intent = getIntent();
        tournID = intent.getStringExtra("TournamentID");

        games = new ArrayList<Game>();
        handler = new Handler();

        ScrollView sv = new ScrollView(this);
        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        sv.addView(layout);

        observeGames();

        this.setContentView(sv);
    }

    /**
     * Fills the listview with the data received from the database
     */

    public void fillList() {
        layout.removeAllViews();
        for (Game g : games) {
            TextView t1 = new TextView(this);
            t1.setText("\n\t" + g.display1());
            t1.setTypeface(null, Typeface.BOLD);
            layout.addView(t1);

            TextView t2 = new TextView(this);
            t2.setText("\t" + g.display2());
            t2.setTypeface(null, Typeface.BOLD);
            layout.addView(t2);

            TextView sp = new TextView(this);
            sp.setText("");
            sp.setTypeface(null, Typeface.BOLD);
            layout.addView(sp);

            View line = new View(this);
            line.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 2));
            line.setBackgroundColor(Color.rgb(150, 240, 153));
            layout.addView(line);
        }

        handler.post(new Runnable() {
            @Override
            public void run() {
            }
        });
    }


    /**
     * Gets the list of games from the database
     */
    public void observeGames() {
        Firebase ref = new Firebase("https://scoresubmission.firebaseio.com/Games/" + tournID);
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
                String court = (String) newPost.get("court");
                String location = (String) newPost.get("location");
                String score1 = (String) newPost.get("score1");
                String score2 = (String) newPost.get("score2");
                String team1name = (String) newPost.get("team1");
                String team2name = (String) newPost.get("team2");
                String time = (String) newPost.get("time");

                // Create the two teams
                Team team1 = new Team(team1name, score1);
                Team team2 = new Team(team2name, score2);

                // Create the game and add it to the list
                Game g = new Game (tournID, court, location, time, id, team1, team2);
                games.add(g);

                // Clear the map between reads
                newPost.clear();

                // Call the method to populate the spinner
                fillList();
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
     * Creates the options menu
     * @param menu The menu
     * @return Boolean
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_notification_board, menu);
        return true;
    }

    /**
     * Returns the item selected
     * @param item The item
     * @return Boolean+++++
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
