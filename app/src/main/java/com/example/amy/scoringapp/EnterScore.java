package com.example.amy.scoringapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Gets the values filled in by the user and calls the game class' method for submitted the created
 * Game class object automatically created
 */
public class EnterScore extends ActionBarActivity {
    private String tournID;       // The id of the tournament selected on the main screen
    private Spinner spinnerHour;  // Hour spinner
    private Spinner spinnerMin;   // Minute spinner
    private Spinner spinnerAMPM;  // AM/PM spinner
    private String location;
    private String courtNum;
    private String team1name;
    private String team1score;
    private String team2name;
    private String team2score;

    /**
     * Fills the spinners with the needed data
     * Gets the tournament id from the previous page
     * @param savedInstanceState The Saved Instance State
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        String hour = spinnerHour.getSelectedItem().toString();
        String min = spinnerMin.getSelectedItem().toString();
        String AMPM = spinnerAMPM.getSelectedItem().toString();
        //location = findViewById(R.id.editTextLocation).toString();
        // TODO: Finish these
        /*courtNum;
        team1name;
        team1score;
        team2name;
        team2score;*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_enter_score, menu);
        return true;
    }

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
