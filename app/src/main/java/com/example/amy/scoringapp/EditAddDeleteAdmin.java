package com.example.amy.scoringapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.firebase.client.Firebase;

/**
 * EDIT ADD DELETE ADMIN
 *   This class will edit and add a new administrator to FireBase.
 *
 *   Contains no class variables.
 */
public class EditAddDeleteAdmin extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_add_delete_admin);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_add_delete_admin, menu);
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

    /*
     * OnClickConfirmEdit
     *  Member creates two EditText boxes for the admin name and password, and a FireBase ref
     *  for pushing the data.
     */
    public void onClickConfirmEdit(View view){

        EditText adminName = (EditText) findViewById(R.id.AdminName);
        EditText adminPassword = (EditText) findViewById(R.id.AdminPassword);

        // creating a new reference to the admin name that will be pushed on.  The way FireBase works,
        // if information is stored under password, the admin name is automatically created, so
        // we do not need to.
        Firebase ref = new Firebase("https://scoresubmission.firebaseio.com/Admin/" + adminName.getText().toString());
        ref.child("Password").setValue(adminPassword.getText().toString());

    }
}
