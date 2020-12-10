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
import android.widget.Button;
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

public class PreparePictureActivity extends AppCompatActivity implements SensorEventListener {
    private static final double THRESHOLD = 50;
    // Sensors Environment Documentation:
    // https://developer.android.com/guide/topics/sensors/sensors_environment
    private SensorManager sensorManager;
    private Sensor light;
    TextView displayLight, waitText;
    GraphView graph;
    Button goToTakePictureButton, restartButton;
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
        setContentView(R.layout.activity_prepare_picture);

        // Get an instance of the sensor service, and use that to get an instance of
        // a particular sensor.
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        displayLight = (TextView) findViewById(R.id.tv_light);
        graph = (GraphView) findViewById(R.id.graph);

        goToTakePictureButton = (Button) findViewById(R.id.btn_gotoTakePicture);
        restartButton = (Button) findViewById(R.id.btn_restart);
        // set button visibility off
        goToTakePictureButton.setVisibility(View.GONE);
        restartButton.setVisibility(View.GONE);

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
            if (dataAvg >= THRESHOLD) {
                waitText.setText("Your average Illuminance in your room is " + dataAvg + ", which is a good amount" +
                        "of light to take a picture for analysis. Proceed to the camera button!");

                // set camera button visibility on
                goToTakePictureButton.setVisibility(View.VISIBLE);
                restartButton.setVisibility(View.GONE);
            }
            else {
                waitText.setText("The average Illuminance in your room is " + dataAvg + ", which is not enough" +
                        "light to take a proper picture for analysis. Please adjust your surroundings as needed and remeasure.");

                // set camera button visibility off
                goToTakePictureButton.setVisibility(View.GONE);
                restartButton.setVisibility(View.VISIBLE);
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

    public void gotoTakePicture(View view) {
        Intent intent = new Intent(this, TakePictureActivity.class);
        startActivity(intent);
    }

    public void restartActivity(View view) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}