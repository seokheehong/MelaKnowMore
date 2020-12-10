package com.example.melaknowmore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText firstNameET, lastNameET, emailET, userNameET, passwordET;
    Button registerBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // register the values
        firstNameET = (EditText) findViewById(R.id.et_firstname);
        lastNameET = (EditText) findViewById(R.id.et_lastname);
        emailET = (EditText) findViewById(R.id.et_email);
        userNameET = (EditText) findViewById(R.id.et_username);
        passwordET = (EditText) findViewById(R.id.et_password);

        registerBTN = (Button) findViewById(R.id.btn_register);

        // when the register button is clicked
        registerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Created a shared preferences variable and associated editor
//                SharedPreferences preferences = getSharedPreferences("MY_PREFERENCES", Context.MODE_PRIVATE);
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();

                // capture the username and password from the editText
                String newFirstName = firstNameET.getText().toString();
                String newLastName = lastNameET.getText().toString();
                String newEmail = emailET.getText().toString();
                String newUserName = userNameET.getText().toString();
                String newPassword = passwordET.getText().toString();

                // check if everything has been answered
                if (newFirstName.length() <= 0 || newLastName.length() <= 0 || newEmail.length() <= 0 || newUserName.length() <= 0 || newPassword.length() <= 0) {
                    Toast.makeText(RegisterActivity.this, "Missing Information! Please fill in everything.", Toast.LENGTH_LONG).show();
                }
                else {
                    // store the values using the editor
                    editor.putString("FirstName", newFirstName);
                    editor.putString("LastName", newLastName);
                    editor.putString("Email", newEmail);
                    editor.putString("Username", newUserName);
                    editor.putString("Password", newPassword);

                    // commit the values
                    editor.apply();

                    // move to the login screen
                    Intent goToLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(goToLogin);
                }
            }
        });
    }

    public void goToLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}