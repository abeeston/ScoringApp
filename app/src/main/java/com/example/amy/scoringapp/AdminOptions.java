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

/**
 * Class that handles the main administrator page options for the app.
 *
 * Variables:
 * A list of available tournaments - Necessary for editing and deleting.
 * A spinner                       - filled by the list of tournaments
 * A handler                       - for multithreading purposes
 * Group of Strings                - contains necessary location, date, password, and id that will
 *                                   be passed onto the next pages.
 */
public class AdminOptions extends ActionBarActivity {

    private List<Tournament> available = new ArrayList<>(); // The list of tournaments to populate the spinner
    private Spinner spinner;                                // Our spinner containing the tournaments
    private Handler handler = new Handler();                // For multithreading when loading tournaments

    private String locationString;
    private String dateString;
    private String passwordString;
    private String idString;

    /*
     * OnCreate will simply fill the spinner with a list of tournaments.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_options);
        observeTournament();
    }

    /*
     * default
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_admin_options, menu);
        return true;
    }

    /*
     * default
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

    /*
     * Fills the spinner with the proper tournament values.
     */
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

        // handler to fill the spinner.
        handler.post(new Runnable() {
            @Override
            public void run() {
            }
        });
    }

    /*
     * In order to upload anything from FireBase, it is necessary to create an event listener and
     * pull off the information.
     *
     * function contains a FireBase reference to Tournaments, and a Query to withdraw the info.
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

                // populate the strings with information.
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

    /*
     * OnClickEdit:
     *   Gathers the information from the spinner that was selected.  Enters into a new intent
     *   and takes the information with it.
     */
    public void onClickEdit(View view) {
        Intent intent = new Intent(this, AdminEditTournament.class);

        // Get the selected item and send the tournament's id for updating the game
        int position = spinner.getSelectedItemPosition();

        String id = available.get(position).getID();
        intent.putExtra("TournamentID", id);

        // Get the location for editing purposes
        String location = available.get(position).getCity();
        intent.putExtra("Location", location);

        // Get the date and send it for editing purposes.
        String date     = available.get(position).getDate();
        intent.putExtra("Date", date);

        // Get the password and send it for editing
        String password = available.get(position).getPassword();
        intent.putExtra("Password", password);

        startActivity(intent);
    }

    /*
     * Goes into the Tournament Creation Activity.
     */
    public void onClickTournCreate(View view) {
        Intent intent = new Intent(this, AdminActivity.class);
        startActivity(intent);
    }

    /*
     * OnClickTournDelete
     *   Takes the tournament ID from the spinner, and removes the tournament and its respective
     *   games from FireBase.
     *
     *   Sends back to the main screen.
     */
    public void onClickTournDelete(View view) {
        // spinner position
        int position = spinner.getSelectedItemPosition();
        String id = available.get(position).getID();

        // we need two firebase references, one for games, one for tournaments.
        Firebase ref = new Firebase("https://scoresubmission.firebaseio.com/Tournaments");
        Firebase refGame = new Firebase("https://scoresubmission.firebaseio.com/Games");

        // removes these values from FireBase
        ref.child(id).removeValue();
        refGame.child(id).removeValue();

        // Toast.
        Toast.makeText(getApplicationContext(), "The Tournament has been deleted.",
                Toast.LENGTH_LONG).show();

        // Returns to main screen, where everything will once again update.
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /*
     * OnClickAdminSettings:
     *   Goes into the admin settings screen.
     */
    public void onClickAdminSettings(View view){
        Intent intent = new Intent (this, EditAddDeleteAdmin.class);
        startActivity(intent);
    }
}
