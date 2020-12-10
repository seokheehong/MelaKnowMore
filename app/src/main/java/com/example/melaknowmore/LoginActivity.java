package com.example.melaknowmore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public static final String EXTRA_FIRST_NAME = "com.example.melaknowmore.EXTRA_FIRST_NAME";
    public static final String EXTRA_LAST_NAME = "com.example.melaknowmore.EXTRA_LAST_NAME";
    public static final String EXTRA_EMAIL = "com.example.melaknowmore.EXTRA_EMAIL";
    public static final String EXTRA_USER_NAME = "com.example.melaknowmore.EXTRA_USER_NAME";
    public static final String EXTRA_PASSWORD = "com.example.melaknowmore.EXTRA_PASSWORD";

    EditText loginUsernameET, loginPasswordET;
    String firstNameSTR, lastNameSTR, emailSTR, usernameSTR, passwordSTR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUsernameET = (EditText) findViewById(R.id.et_login_username);
        loginPasswordET = (EditText) findViewById(R.id.et_login_password);
    }

    public void onLogin(View view) {
        // Create intent to move to Home Screen
        Intent intent = new Intent(this, MainActivity.class);

        // Bring in the SharedPreferences object
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        // Bring in SharedPref values
        String nullValue = "null";
        String PREFusername = preferences.getString("Username", nullValue);
        String PREFpassword = preferences.getString("Password", nullValue);
        String PREFfirstname = preferences.getString("FirstName", nullValue);
        String PREFlastname = preferences.getString("LastName", nullValue);
        String PREFemail = preferences.getString("Email", nullValue);


        // check if username and password matches
        if (loginUsernameET.getText().toString().equals(PREFusername) && loginPasswordET.getText().toString().equals(PREFpassword)) {
            Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();
            // set FINAL values
            intent.putExtra(EXTRA_USER_NAME, PREFusername);
            intent.putExtra(EXTRA_PASSWORD, PREFpassword);
            intent.putExtra(EXTRA_FIRST_NAME, PREFfirstname);
            intent.putExtra(EXTRA_LAST_NAME, PREFlastname);
            intent.putExtra(EXTRA_EMAIL, PREFemail);

            // move to home screen!
            startActivity(intent);
        }
        else if (loginUsernameET.length() <= 0 || loginPasswordET.length() <= 0) {
            Toast.makeText(this, "Please fill in the boxes.", Toast.LENGTH_SHORT).show();
        }
        else if (loginUsernameET.getText().toString().equals(PREFusername)) {
            Toast.makeText(this, "Correct username, incorrect password. Please retry.", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Incorrect username and password. Please retry or make an account.", Toast.LENGTH_SHORT).show();
        }
    }

    public void goToRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void goToHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}