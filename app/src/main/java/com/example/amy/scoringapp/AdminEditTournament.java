package com.example.amy.scoringapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;


/**
 * Activity page for an administrator to edit
 * an existing tournament.
 */
public class AdminEditTournament extends ActionBarActivity {

    private String tournID;
    private String location;
    private String date;
    private String password;

    private EditText locationEdit;
    private EditText dateEdit;
    private EditText passwordEdit;

    private Firebase refTourn;

    /**
     * Tranfer instance states
     * @param savedInstanceState state of the instances
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit);

        // Get data passed from previous activity
        Intent intent = getIntent();

        tournID = intent.getStringExtra("TournamentID");
        location = intent.getStringExtra("Location");
        date = intent.getStringExtra("Date");
        password = intent.getStringExtra("Password");

        // Get the content of the fields
        locationEdit = (EditText) findViewById(R.id.AdminTournName);
        locationEdit.setText (location, TextView.BufferType.EDITABLE);

        dateEdit = (EditText)findViewById(R.id.AdminEditDate);
        dateEdit.setText     (date, TextView.BufferType.EDITABLE);

        passwordEdit = (EditText)findViewById(R.id.AdminEditPassword);
        passwordEdit.setText (password, TextView.BufferType.EDITABLE);

        refTourn = new Firebase("https://scoresubmission.firebaseio.com/Tournaments/" + tournID);
    }

    /**
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_admin_edit, menu);
        return true;
    }

    /**
     * Selects the options item
     * @param item
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
     * Passes the data to the next activity
     * @param view View
     */
    public void onClickAcceptEdit(View view) {

        refTourn.child("location").setValue(locationEdit.getText().toString());
        refTourn.child("date").setValue(dateEdit.getText().toString());
        refTourn.child("password").setValue(passwordEdit.getText().toString());

        Toast.makeText(getApplicationContext(), "Edit successful!",
                Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

