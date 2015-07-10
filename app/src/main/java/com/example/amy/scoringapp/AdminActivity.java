package com.example.amy.scoringapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;

/**
 *  Admin Activity controls things that only the admin can do.
 *
 */
public class AdminActivity extends ActionBarActivity {

    private EditText tournName, tournDate, tournPassword;

    /**
     * Loads the necessary items
     * @param savedInstanceState The saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    /**
     * Creates the options menu
     * @param menu The menu
     * @return Boolean
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return true;
    }

    /**
     * Returns the item selected
     * @param item The item selected
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

    /**
     * When this button is clicked, we will push the tournament information onto the
     * database.
     * @param view The view
     */
    public void onClickAdminActivity(View view){

        tournName     = (EditText) findViewById(R.id.Tournament);
        tournDate     = (EditText) findViewById(R.id.TournDate);
        tournPassword = (EditText) findViewById(R.id.TournPassword);

        //creates reference to games in tournament
        Firebase ref = new Firebase("https://scoresubmission.firebaseio.com/Tournaments");

        //create and push variables to hash map
        Map<String, String> post = new HashMap<>();
        post.put("date", tournDate.getText().toString());
        post.put("location", tournName.getText().toString());
        post.put("password", tournPassword.getText().toString());
        ref.push().setValue(post);

        Toast.makeText(getApplicationContext(), "Tournament added successfully",
                Toast.LENGTH_LONG).show();
    }
}
