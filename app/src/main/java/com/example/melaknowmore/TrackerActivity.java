//package com.example.melaknowmore;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//public class TrackerActivity extends RecyclerView.ViewHolder {
//
//    public ImageView imgThumbnail;
//    public TextView tvNature;
//    public TextView tvDesNature;
//
//    public TrackerActivity(View itemView) {
//        super(itemView);
//        imgThumbnail = (ImageView)itemView.findViewById(R.id.img_thumbnail);
//        tvNature = (TextView)itemView.findViewById(R.id.tv_nature);
//        tvDesNature = (TextView)itemView.findViewById(R.id.tv_des_nature);
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                v.getContext().startActivity(new Intent(v.getContext(),CardInfo.class));
//
//            }
//        });
//    }
//
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_tracker);
////    }
//}