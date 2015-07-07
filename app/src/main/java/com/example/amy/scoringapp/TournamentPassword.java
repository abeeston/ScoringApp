package com.example.amy.scoringapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Verifies the password for a given tournament
 */
public class TournamentPassword extends ActionBarActivity {
    private String tournID;

    /**
     * Gets the tournament id for verifying
     * @param savedInstanceState The Saved Instance State
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_password);

        Intent intent = getIntent();
        tournID = intent.getStringExtra("TournamentID");


    }

    /**
     * Creates the options menu
     * @param menu The menu
     * @return Boolean
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tournament_password, menu);
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
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Verify the password and if it is valid start the next activity
     * @param view The View
     */
    public void onClickSubmitTournamentPassword(View view) {
        // TODO: Verify the password


        // Create an intent to put the tournament in and send it to the ScoreSubmit class and activity
        Intent intent = new Intent(this, EnterScore.class);
        intent.putExtra("TournamentID", tournID);

        startActivity(intent);
    }
}
