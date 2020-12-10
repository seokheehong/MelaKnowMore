package com.example.melaknowmore;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);


        // Login activities
        TextView welcomeText = (TextView) v.findViewById(R.id.textViewWelcome);
        Intent mainIntent = getActivity().getIntent();

        //create a shared preferences variable and associated editor
        SharedPreferences loginInfo = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor loginEditor = loginInfo.edit();

        // get the time and date to record log-in  ----------
        long dateTime = System.currentTimeMillis();
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MMM dd yyyy\nhh:mm a");
        String dateTimeString = dateTimeFormat.format(dateTime);

        // grab the user's FIRST_NAME
        String loginName = mainIntent.getStringExtra(LoginActivity.EXTRA_FIRST_NAME);

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
                if (loginName == null) {
                    loginName = "Guest";
                    //set welcome message
                    welcomeText.setText("Hi there, stranger!");
                }
                else {
                    //set welcome message
                    welcomeText.setText("Welcome back, " + loginName + "!\n\n Your last log-in was:\n"+ lastLogin);
                }
            }
            // update log with new login time
            //TODO
            loginEditor.putString(loginName,dateTimeString);
            loginEditor.apply();

            return v;
            }


}
