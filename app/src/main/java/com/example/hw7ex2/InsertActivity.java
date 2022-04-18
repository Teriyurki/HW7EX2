package com.example.hw7ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        setContentView(R.layout.activity_insert);
    }

    public void insert(View view) {
        // Retrieve first name, last name, and email
        Log.w("InsertActivity", "Insert Button Pushed");

        EditText fnameET = findViewById(R.id.input_fname);
        EditText lnameET = findViewById(R.id.input_lname);
        EditText emailET = findViewById(R.id.input_email);
        String fname = fnameET.getText().toString();
        String lname = lnameET.getText().toString();
        String email = emailET.getText().toString();

        //insert into database

        Friends friends = new Friends(0, fname, lname, email);
        dbManager.insert(friends);
        Toast.makeText(this, "Friend Added", Toast.LENGTH_SHORT).show();

        //clear data
        fnameET.setText("");
        lnameET.setText("");
        emailET.setText("");


    }

    public void goBack(View view) {

        this.finish();

    }
}