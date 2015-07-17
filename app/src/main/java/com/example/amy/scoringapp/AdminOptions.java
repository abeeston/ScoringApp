package com.example.amy.scoringapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AdminOptions extends ActionBarActivity {

    private List<Tournament> available = new ArrayList<>();      // The list of tournaments to populate the spinner
    private Spinner spinner;                 // Our spinner containing the tournaments
    private Handler handler = new Handler();                 // For multithreading when loading tournaments

    private EditText location;
    private EditText date;
    private EditText password;

    private String locationString;
    private String dateString;
    private String passwordString;
    private String idString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_options);
        observeTournament();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_admin_options, menu);
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




    public void fillSpinner() {
        List<String> stringList = new ArrayList<>();

        // Go through all of the tournaments in available
        for (Tournament t : available) {
            stringList.add(t.display());
        }

        // Use the array adapter to change the contents of the spinner
        spinner = (Spinner) findViewById(R.id.SpinnerEdit);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, stringList);
        spinner.setAdapter(adapter);

        handler.post(new Runnable() {
            @Override
            public void run() {
            }
        });
    }


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

                idString = dataSnapshot.getKey();
                dateString = (String) newPost.get("date");
                locationString = (String) newPost.get("location");
                passwordString = (String) newPost.get("password");

                // Create the tournament and add it to the list
                Tournament t = new Tournament(idString, dateString, locationString, passwordString);
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



    public void onClickEdit(View view) {
        Intent intent = new Intent(this, AdminEditTournament.class);

        // Get the selected item and send the tournament's id for updating the game
        int position = spinner.getSelectedItemPosition();

        String id = available.get(position).getID();
        intent.putExtra("TournamentID", idString);

        // Get the location for editing purposes
        String location = available.get(position).getCity();
        intent.putExtra("Location", locationString);

        // Get the date and send it for editing purposes.
        String date     = available.get(position).getDate();
        intent.putExtra("Date", dateString);

        // Get the password and send it for editing
        String password = available.get(position).getPassword();
        intent.putExtra("Password", passwordString);

        System.out.println(locationString + passwordString + dateString + id);

        startActivity(intent);
    }

    public void onClickTournCreate(View view) {
        Intent intent = new Intent(this, AdminActivity.class);
        startActivity(intent);
    }

    public void onClickTournDelete(View view) {
        int position = spinner.getSelectedItemPosition();
        String id = available.get(position).getID();

        Firebase ref = new Firebase("https://scoresubmission.firebaseio.com/Tournaments");
        Firebase refGame = new Firebase("https://scoresubmission.firebaseio.com/Games");
        ref.child(id).removeValue();
        refGame.child(id).removeValue();

        Toast.makeText(getApplicationContext(), "The Tournament has been deleted.",
                Toast.LENGTH_LONG).show();

        observeTournament();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
