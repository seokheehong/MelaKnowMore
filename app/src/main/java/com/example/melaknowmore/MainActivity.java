package com.example.melaknowmore;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;


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
    }

    public void goToTakePicture(View view) {
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