package com.example.amy.scoringapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;


public class AdminEditTournament extends ActionBarActivity {

    private String tournID;
    private String location;
    private String date;
    private String password;

    EditText locationEdit;
    EditText dateEdit;
    EditText passwordEdit;

    Firebase refTourn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit);

        Intent intent = getIntent();

        tournID = intent.getStringExtra("TournamentID");
        location = intent.getStringExtra("Location");
        date = intent.getStringExtra("Date");
        password = intent.getStringExtra("Password");

        System.out.println(tournID + location + date + password);

        EditText locationEdit = (EditText) findViewById(R.id.AdminTournName);
        locationEdit.setText (location, TextView.BufferType.EDITABLE);

        EditText dateEdit = (EditText)findViewById(R.id.AdminEditDate);
        dateEdit.setText     (date, TextView.BufferType.EDITABLE);

        EditText passwordEdit = (EditText)findViewById(R.id.AdminEditPassword);
        passwordEdit.setText (password, TextView.BufferType.EDITABLE);

        refTourn = new Firebase("https://scoresubmission.firebaseio.com/Tournaments");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_admin_edit, menu);
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

    public void onClickAcceptEdit(View view) {
        //refTourn = refTourn.child(tournID);


        refTourn.child("location").setValue(location);
        refTourn.child("date").setValue(date);
        refTourn.child("password").setValue(password);
    }
}

