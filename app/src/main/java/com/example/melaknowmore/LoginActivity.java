package com.example.melaknowmore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText usernameEditText, passwordEditText;

    public static final String EXTRA_USER_NAME = "com.example.melaknowmore.EXTRA_USER_NAME";
    public static final String EXTRA_PASSWORD = "com.example.melaknowmore.EXTRA_PASSWORD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // access the editText with the userName
        usernameEditText = (EditText) findViewById(R.id.editTextUsername);
        passwordEditText = (EditText) findViewById(R.id.editTextPassword);
    }

    public void onLogin(View view) {
        // capture the username and password from the editText
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // create intent with extra information
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(EXTRA_USER_NAME, username);
        intent.putExtra(EXTRA_PASSWORD, username);

        // go to next activity
        startActivity(intent);
    }

    public void goToHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}