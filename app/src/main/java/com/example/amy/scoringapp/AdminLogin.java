package com.example.amy.scoringapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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
 *  Handles the admin login
 */
public class AdminLogin extends ActionBarActivity {

    private List<Admin> admins;
    private EditText username;
    private EditText password;

    /**
     * Starts and initializes variables
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        username = (EditText) findViewById(R.id.AdminUsername);
        password = (EditText) findViewById(R.id.AdminPassword);

        admins = new ArrayList<>();

        getAdmins();
    }


    /**
     * Gets the list of tournaments from the database
     */
    public void getAdmins() {
        Firebase ref = new Firebase("https://scoresubmission.firebaseio.com/Admin");
        Query queryRef = ref.orderByKey();

        /**
         * Listens for added children in the database
         */
        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                // Read the data into a map
                Map<String, Object> newPost = (HashMap<String, Object>) dataSnapshot.getValue();

                String name = dataSnapshot.getKey();
                String password = (String) newPost.get("Password");

                // Create the administrator and add it to the list
                Admin a = new Admin(name, password);
                admins.add(a);

                // Clear the map between reads
                newPost.clear();
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
        getMenuInflater().inflate(R.menu.menu_admin_login, menu);
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

    /**
     * Goes into the next screen and passes the information to it.
     * @param view The view
     */
    public void onClickAdminLogin(View view) {
        String user = username.getText().toString();
        String pass = password.getText().toString();

        Admin entered = new Admin(user, pass);
        for (Admin a : admins) {
            if (true /*entered.equalTo(a)*/) {
                System.out.println("Inside!");
                Intent intent = new Intent(this, AdminOptions.class);
                startActivity(intent);
            }
        }
        Toast.makeText(getApplicationContext(), "The credentials were not correct. Please try again",
                Toast.LENGTH_LONG).show();
    }
}
