package com.example.melaknowmore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrackerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrackerFragment extends Fragment {

    private final String image_titles[] = {
            "Img1",
            "Img2",
//            "Img3",
//            "Img4",
//            "Img5",
//            "Img6",
//            "Img7",
//            "Img8",
//            "Img9",
//            "Img10",
//            "Img11",
//            "Img12",
//            "Img13",
    };

    private final Integer image_ids[] = {
            R.drawable.about_melanoma,
            R.drawable.about_melanoma,
//            R.drawable.img3,
//            R.drawable.img4,
//            R.drawable.img5,
//            R.drawable.img6,
//            R.drawable.img7,
//            R.drawable.img8,
//            R.drawable.img9,
//            R.drawable.img10,
//            R.drawable.img11,
//            R.drawable.img12,
//            R.drawable.img13,
    };



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TrackerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TrackerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TrackerFragment newInstance(String param1, String param2) {
        TrackerFragment fragment = new TrackerFragment();
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
        View v = inflater.inflate(R.layout.fragment_tracker, container, false);

//        getActivity().setContentView(R.layout.activity_main);
//
//        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.imagegallery);
//        recyclerView.setHasFixedSize(true);
//
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(),2);
//        recyclerView.setLayoutManager(layoutManager);
//        ArrayList<CreateList> createLists = prepareData();
//        MyAdapter adapter = new MyAdapter(getActivity().getApplicationContext(), createLists);
//        recyclerView.setAdapter(adapter);

        return v;
    }




//    private ArrayList<CreateList> prepareData(){
//
//        ArrayList<CreateList> theimage = new ArrayList<>();
//        for(int i = 0; i< image_titles.length; i++){
//            CreateList createList = new CreateList();
//            createList.setImage_title(image_titles[i]);
//            createList.setImage_ID(image_ids[i]);
//            theimage.add(createList);
//        }
//        return theimage;
//    }
}