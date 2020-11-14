package com.example.melaknowmore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static androidx.core.content.FileProvider.getUriForFile;

public class TakePictureActivity extends AppCompatActivity implements SensorEventListener {
    // Sensors Environment Documentation:
    // https://developer.android.com/guide/topics/sensors/sensors_environment
    private SensorManager sensorManager;
    private Sensor light;
    TextView displayLight, waitText;
    GraphView graph;
    int data0 = 0;
    int data1 = 0;
    int data2 = 0;
    int data3 = 0;
    int data4 = 0;
    int data5 = 0;
    int data6 = 0;
    int data7 = 0;
    int data8 = 0;
    int data9 = 0;
    double dataAvg = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);

        // Take picture prep
        filename = (TextView) findViewById(R.id.tv_filename);
        thumbnail = (ImageView) findViewById(R.id.iv_thumbnail);
        fullImage = (ImageView) findViewById(R.id.iv_fullImage);

        ImageButton photoBtn = (ImageButton) findViewById(R.id.imgbtn_Camera);
        Log.d(TAG, "activity created");

        // Get an instance of the sensor service, and use that to get an instance of
        // a particular sensor.
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        displayLight = (TextView) findViewById(R.id.tv_light);
        graph = (GraphView) findViewById(R.id.graph);

        // emailing the data
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL  , new String[]{"Recipient"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT   , "Message Body");

        // send email of the collected data

    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }


    public final void onSensorChanged(SensorEvent event) {
        float lxOfLight = event.values[0];                      // lx = illuminance
        waitText = (TextView) findViewById(R.id.tv_wait);
        // Do something with this sensor data.

        displayLight.setText(String.valueOf(lxOfLight));

        if (data0 == 0) {
            data0 = (int) lxOfLight;
        }
        else if (data1 == 0) {
            data1 = (int) lxOfLight;
        }
        else if (data2 == 0) {
            data2 = (int) lxOfLight;
        }
        else if (data3 == 0) {
            data3 = (int) lxOfLight;
        }
        else if (data4 == 0) {
            data4 = (int) lxOfLight;
        }
        else if (data5 == 0) {
            data5 = (int) lxOfLight;
        }
        else if (data6 == 0) {
            data6 = (int) lxOfLight;
        }
        else if (data7 == 0) {
            data7 = (int) lxOfLight;
        }
        else if (data8 == 0) {
            data8 = (int) lxOfLight;
        }
        else if (data9 == 0) {
            data9 = (int) lxOfLight;
        }
        else {

            dataAvg = (data0 + data1 + data2 + data3 + data4 + data5 + data6 + data7 + data8 + data9) / 10.0;
            LineGraphSeries<DataPoint> dataGraph = new LineGraphSeries<>(new DataPoint[] {
                    new DataPoint(0, data0),
                    new DataPoint(1, data1),
                    new DataPoint(2, data2),
                    new DataPoint(3, data3),
                    new DataPoint(4, data4),
                    new DataPoint(5, data5),
                    new DataPoint(6, data6),
                    new DataPoint(7, data7),
                    new DataPoint(8, data8),
                    new DataPoint(9, data9)
            });
            graph.addSeries(dataGraph);

            LineGraphSeries<DataPoint> avgGraph= new LineGraphSeries<>(new DataPoint[] {
                    new DataPoint(0, dataAvg),
                    new DataPoint(1, dataAvg),
                    new DataPoint(2, dataAvg),
                    new DataPoint(3, dataAvg),
                    new DataPoint(4, dataAvg),
                    new DataPoint(5, dataAvg),
                    new DataPoint(6, dataAvg),
                    new DataPoint(7, dataAvg),
                    new DataPoint(8, dataAvg),
                    new DataPoint(9, dataAvg)
            });
            graph.addSeries(avgGraph);

            LineGraphSeries<DataPoint> minGraph = new LineGraphSeries<>(new DataPoint[] {
                    new DataPoint(0, 150),
                    new DataPoint(1, 150),
                    new DataPoint(2, 150),
                    new DataPoint(3, 150),
                    new DataPoint(4, 150),
                    new DataPoint(5, 150),
                    new DataPoint(6, 150),
                    new DataPoint(7, 150),
                    new DataPoint(8, 150),
                    new DataPoint(9, 150)
            });
            graph.addSeries(minGraph);

            // Graph Styling
            avgGraph.setColor(Color.GREEN);
            minGraph.setColor(Color.RED);

            // Change the waitText with the average illuminance and proper corresponding procedures.
            if (dataAvg >= 150) {
                waitText.setText("Your average Illuminance in your room is " + dataAvg + ", which is a good amount" +
                        "of light to take a picture for analysis. Proceed to the camera button!");
            }
            else {
                waitText.setText("The average Illuminance in your room is " + dataAvg + ", which is not enough" +
                        "light to take a proper picture for analysis. Please adjust your surroundings as needed and remeasure.");
            }


        }


    }

    @Override
    protected void onResume() {
        // Register a listener for the sensor.
        super.onResume();
        sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    // Take picture part ---------------------------------------------------------------------------
    static final int REQUEST_IMAGE_CAPTURE = 100;
    static final int REQUEST_TAKE_PHOTO = 1;
    static final String TAG = "Camera Activity";
    private ImageView thumbnail, fullImage;
    private String currentPhotoPath;
    private TextView filename;

   /*private void dispatchTakePictureIntent() {
        Log.d(TAG, "enter easy dispatch");
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {
            // display error state to the user
        }
        Log.d(TAG, "exit easy dispatch");
    }*/

    public void takePhoto(View v){
        Log.d(TAG, "start takePhoto");
        dispatchTakePictureIntent();
    }

    public void takeCustomPhoto (View v){
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }

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
                        photoFile);                             // ^ shh - changed appropriately
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
        Log.d(TAG,"entering onActivityResult");

        //handle case for thumbnail image only
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            thumbnail.setImageBitmap(imageBitmap);
        }
        //handle for full image case
        else if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            Bitmap imageBitmap = BitmapFactory.decodeFile(currentPhotoPath);
            fullImage.setImageBitmap(imageBitmap);
        }
        Log.d(TAG,"exit onActivityResult");
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  ///* prefix */
                ".jpg",  //       /* suffix */
                storageDir     // /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
}