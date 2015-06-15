package com.example.amy.scoringapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.client.Firebase;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private Firebase myFirebaseRef;
    private ArrayList<Tournament> available;
    String tournaments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://popping-torch-5466.firebaseio.com/");
        setContentView(R.layout.activity_main);


        myFirebaseRef.child("message").setValue("Do you have data? You'll love Firebase.");
        System.out.println("Checkitchekcititehiosadhfoihadsiohf");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml
        // Commit this.  Yeahhhhh....
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onClickReturnScore(){

        return true;
    }

    public boolean onClickNotificationBoard(){

        return true;
    }

    public void onChangeActiveTournament(){

    }
}
