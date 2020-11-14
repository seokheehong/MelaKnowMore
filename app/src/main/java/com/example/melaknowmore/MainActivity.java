package com.example.melaknowmore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // establish the navigation abr and its selection listener
        navView = (BottomNavigationView) findViewById(R.id.bnv_navbar);
        navView.setOnNavigationItemSelectedListener(bottomNavMethod);

        // set default loaded fragment to HOME
        Fragment defaultFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, defaultFragment).commit();

        //--------------------------------------------------------------------------------------
        // Login activities
        TextView welcomeText = (TextView) findViewById(R.id.textViewWelcome);
        Intent mainIntent = getIntent();

        //create a shared preferences variable and associated editor
        SharedPreferences loginInfo = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor loginEditor = loginInfo.edit();

        // get the time and date to record log-in  ----------
        long dateTime = System.currentTimeMillis();
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MMM dd yyyy\nhh:mm a");
        String dateTimeString = dateTimeFormat.format(dateTime);

        //check if login already exists  ----------
        //email is the key and logindate is the value
        //TODO: get login name
        String loginName = mainIntent.getStringExtra(LoginActivity.EXTRA_USER_NAME);
        String passwordName = mainIntent.getStringExtra(LoginActivity.EXTRA_PASSWORD);

        //TODO: load value from preferences (set default return value)
        String nullReturnValue = "not here";
        String lastLogin = loginInfo.getString(loginName, nullReturnValue);

        //if exists, respond with last log-in information and update with new login info
        //if doesn't exist, create new log-in
        if (lastLogin == nullReturnValue){
            //set welcome message
            welcomeText.setText("First time login - welcome!\n");
        }
        else {
            //set welcome message
            welcomeText.setText("Welcome back " + loginName + "!\n\n Your last log-in was:\n"+ lastLogin);

        }
        // update log with new login time
        //TODO
        loginEditor.putString(loginName,dateTimeString);
        loginEditor.apply();

    }

    public void goToTakePicture(View view) {
        Intent intent = new Intent(this, TakePictureActivity.class);
        startActivity(intent);
    }

    public void goToContactDoctor(View view) {
        Intent intent = new Intent(this, TakePictureActivity.class);
        startActivity(intent);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            // create the fragment object
            Fragment fragment = null;

            // set fragment object to the appropriate selection
            switch (item.getItemId()) {
                case R.id.nmi_home:
                    fragment = new HomeFragment();
                    break;
                case R.id.nmi_tracker:
                    fragment = new TrackerFragment();
                    break;
                case R.id.nmi_profile:
                    fragment = new ProfileFragment();
                    break;
            }

            // load the fragment into the frame layout container
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment).commit();
            return true;
        }
    };


//    private Toolbar myToolbar;
//
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        myToolbar = (Toolbar) findViewById(R.id.myToolbar);
//        setSupportActionBar(myToolbar);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menu_diagnose:
//                return true;
//            case R.id.menu_more_info:
//                Intent intent = new Intent(this, MoreInfoActivity.class);
//                startActivity(intent);
//                return true;
//            case R.id.menu_about_us:
//                return true;
//            case R.id.menu_contact:
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }


}