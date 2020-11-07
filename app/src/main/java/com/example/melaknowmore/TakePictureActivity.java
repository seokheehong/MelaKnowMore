package com.example.melaknowmore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class TakePictureActivity extends AppCompatActivity implements SensorEventListener {
    // Sensors Environment Documentation:
    // https://developer.android.com/guide/topics/sensors/sensors_environment
    private SensorManager sensorManager;
    private Sensor light;
    TextView displayLight;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);

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
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
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
            graph.addSeries(series);
        }
    }

//    LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
//            new DataPoint(0, 1),
//            new DataPoint(1, 5),
//            new DataPoint(2, 3),
//            new DataPoint(3, 2),
//            new DataPoint(4, 6)
//    });
//    graph.addSeries(series);

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

}