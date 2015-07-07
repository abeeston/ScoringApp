package com.example.amy.scoringapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.firebase.client.Firebase;

/**
 * Gets the values filled in by the user and calls the game class' method for submitted the created
 * Game class object automatically created
 */
public class EnterScore extends ActionBarActivity {
    private Firebase ref;
    private String tournID;       // The id of the tournament selected on the main screen
    private String time;
    private Spinner spinnerHour;  // Hour spinner
    private Spinner spinnerMin;   // Minute spinner
    private Spinner spinnerAMPM;  // AM/PM spinner
    private EditText location;
    private EditText courtNum;
    private EditText team1name;
    private EditText team1score;
    private EditText team2name;
    private EditText team2score;

    /**
     * Fills the spinners with the needed data
     * Gets the tournament id from the previous page
     * @param savedInstanceState The Saved Instance State
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ref = new Firebase("https://scoresubmission.firebaseio.com/");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_score);

        // Fill the spinner with the hours
        spinnerHour = (Spinner) findViewById(R.id.spinnerHour);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.hours_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHour.setAdapter(adapter);

        // Fill the spinner with the minutes
        spinnerMin = (Spinner) findViewById(R.id.spinnerMinute);
        ArrayAdapter<CharSequence> adapterMin = ArrayAdapter.createFromResource(this, R.array.minute_array, android.R.layout.simple_spinner_item);
        adapterMin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMin.setAdapter(adapterMin);

        // Fill the spinner with AM and PM
        spinnerAMPM = (Spinner) findViewById(R.id.spinnerAMPM);
        ArrayAdapter<CharSequence> adapterAMPM = ArrayAdapter.createFromResource(this, R.array.am_pm, android.R.layout.simple_spinner_item);
        adapterAMPM.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAMPM.setAdapter(adapterAMPM);

        // Get the selected Tournament's ID. This will be used for creating games
        Intent intent = getIntent();
        tournID = intent.getStringExtra("TournamentID");
    }

    /**
     * Gets the data from the filled in fields, uses the Game constructor to create the game and
     * calls the game object's submit method
     * @param v The View
     */
    public void onClickSubmit(View v) {
        // Get the time and format it
        String hour = spinnerHour.getSelectedItem().toString();
        String min = spinnerMin.getSelectedItem().toString();
        String AMPM = spinnerAMPM.getSelectedItem().toString();
        time = hour + ":" + min + " " + AMPM;

        // Get the game data
        location = (EditText) findViewById(R.id.GameLocation);
        courtNum = (EditText) findViewById(R.id.CourtNumber);
        team1name = (EditText) findViewById(R.id.Team1Name);
        team1score = (EditText) findViewById(R.id.ScoreTeam1);
        team2name = (EditText) findViewById(R.id.Team2Name);
        team2score = (EditText) findViewById(R.id.ScoreTeam2);

        Team team1 = new Team(team1name.getText().toString(), team1score.getText().toString());
        Team team2 = new Team(team2name.getText().toString(), team2score.getText().toString());

        // Push the data
        Game game = new Game(tournID, courtNum.getText().toString(), location.getText().toString(), time, team1, team2);
        game.pushData(ref);
    }

    /**
     * Creates the options menu
     * @param menu The menu
     * @return Boolean
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_enter_score, menu);
        return true;
    }

    /**
     * Returns the item selected
     * @param item The item
     * @return Boolean
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
