package com.example.melaknowmore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


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

    public void gotoPreparePicture(View view) {
        Intent intent = new Intent(this, PreparePictureActivity.class);
        startActivity(intent);
    }

    public void goToContactDoctor(View view) {
        Intent intent = new Intent(this, ContactDoctorActivity.class);
        startActivity(intent);
    }

    public void goToTracker(View view) {
        Intent intent = new Intent(this, TrackerActivity.class);
        startActivity(intent);
    }

    public void goToAboutUs(View view) {
        Intent intent = new Intent(this, AboutUsActivity.class);
        startActivity(intent);
    }

    public void openACSWebsite(View view) {
        String url = "https://www.cancer.org/cancer/melanoma-skin-cancer.html";
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        else {
            Log.d("ImplicitIntents", "Can't handle");
        }
    }

    public void openBackgroundInfoWeb(View view) {
        String url = "https://redcap.vanderbilt.edu/surveys/?s=LF9LFLFPHX";
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        else {
            Log.d("ImplicitIntents", "Can't handle");
        }
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod = new BottomNavigationView.OnNavigationItemSelectedListener() {
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