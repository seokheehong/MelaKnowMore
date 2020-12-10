package com.example.melaknowmore;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static androidx.core.content.FileProvider.*;

public class TakePictureActivity extends AppCompatActivity {

    public static final String EXTRA_PHOTO_PATH = "com.example.melaknowmore.EXTRA_PHOTO_PATH";

    static final int REQUEST_IMAGE_CAPTURE = 100;
    static final int REQUEST_TAKE_PHOTO = 1;
    static final String TAG = "Camera Activity";
    private ImageView thumbnail, fullImage;
    private String currentPhotoPath, finalPhotoPath;
    private TextView filename;
    Button goToTrackerBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);

        filename = (TextView) findViewById(R.id.tv_filename);
//        thumbnail = (ImageView) findViewById(R.id.iv_thumbnail);
        fullImage = (ImageView) findViewById(R.id.iv_fullImage);

        goToTrackerBTN = (Button) findViewById(R.id.btn_goToTracker);
        goToTrackerBTN.setVisibility(View.GONE);
        ImageButton photoBtn = (ImageButton) findViewById(R.id.btn_takePicture);
        Log.d(TAG, "activity created");
    }

    public void takePhoto(View v){
        Log.d(TAG, "start takePhoto");
        dispatchTakePictureIntent();
    }

//    public void takeCustomPhoto (View v){
//        Intent intent = new Intent(this, CameraActivity.class);
//        startActivity(intent);
//    }

    private void dispatchTakePictureIntent() {
        Log.d(TAG, "start dispatch");
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            Log.d(TAG, "camera exists");
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
                filename.setText(currentPhotoPath);
            } catch (IOException ex) {
                // Error occurred while creating the File
                //...
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Log.d(TAG, "file successfully created");
                Log.d(TAG, "check 1");
                Uri photoURI = getUriForFile(this,
                        "com.example.melaknowmore.fileprovider",  //note that this is my package
                        photoFile);
                // Uri photoURI = Uri.fromFile(photoFile);
                Log.d(TAG, "check 2");
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                Log.d(TAG, "check 3");
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                Log.d(TAG,"starting camera");
            }
        }
        Log.d(TAG,"exit dispatch");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "entering onActivityResult");

        // handle case for thumbnail image only
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            thumbnail.setImageBitmap(imageBitmap);
        }
        // handle for full image case
        else if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            Bitmap imageBitmap = BitmapFactory.decodeFile(currentPhotoPath);
            fullImage.setImageBitmap(imageBitmap);
        }
        Log.d(TAG, "exit onActivityResult");

        // SAVE THE IMAGE
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            goToTrackerBTN.setVisibility(View.VISIBLE);
            if (resultCode == Activity.RESULT_OK) {
                File f = new File (currentPhotoPath);
                fullImage.setImageURI(Uri.fromFile(f));
                Log.d("TAG", "The absolute URL of image is " + Uri.fromFile(f));
                //finalPhotoPath = Uri.fromFile(f).toString();
                // save to gallery
                Intent mediaScanIntent = new Intent (Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(f);
                mediaScanIntent.setData(contentUri);
                this.sendBroadcast(mediaScanIntent);
            }
        }
        // Display toast message
        Context context = getApplicationContext();
        CharSequence text = "Your image has been saved automatically to your gallery!";
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context, text, duration).show();
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";

        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES); // saves the files to a private location
        //File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES); // allows app to save pictures to gallery
        // the line above does not work due to deprecation of the method for android sdk 29 and up
        File image = File.createTempFile(
                imageFileName,  ///* prefix */
                ".jpg",  //       /* suffix */
                storageDir     // /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();

        return image;
    }

    public void goToTracker(View view) {
        Intent intent = new Intent(this, TrackerActivity.class);
        intent.putExtra(EXTRA_PHOTO_PATH, finalPhotoPath);
        startActivity(intent);
    }

}