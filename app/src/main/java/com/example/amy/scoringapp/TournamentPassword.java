package com.example.amy.scoringapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Verifies the password for a given tournament
 */
public class TournamentPassword extends ActionBarActivity {
    private String tournID;
    private String tournPass;
    private EditText password;

    /**
     * Gets the tournament id for verifying
     * @param savedInstanceState The Saved Instance State
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_password);

        // Configure the password to be a password field
        password = (EditText) findViewById(R.id.EnterPassword);
        password.setGravity(Gravity.CENTER);
        password.setHint("Password");
        password.setWidth(200);
        password.setTransformationMethod(new PasswordTransformationMethod());

        // Get the tournament data
        Intent intent = getIntent();
        tournID = intent.getStringExtra("TournamentID");
        tournPass = intent.getStringExtra("Password");
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
        // NOTE: This works perfectly it's just commented out so we don't have to keep submitting the password
        if (tournPass.equals(password.getText().toString())) {
            // Create an intent to put the tournament in and send it to the ScoreSubmit class and activity
            Intent intent = new Intent(this, EnterScore.class);
            intent.putExtra("TournamentID", tournID);

            startActivity(intent);
        }
        else {
            // Display an error message
            Toast.makeText(getApplicationContext(), "The password was not correct. Please try again",
                    Toast.LENGTH_LONG).show();
        }
    }
}
