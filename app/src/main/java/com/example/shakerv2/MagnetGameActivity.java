package com.example.shakerv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MagnetGameActivity extends AppCompatActivity {
    SensorManager sensorManager;
    SensorEventListener listener;
    Sensor accelerometer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magnet_game);

        ImageView imageView = findViewById(R.id.imageView);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragmentContainer, SensorReaderXYZ.class, null, "sensorView")
                .commit();

//        SensorReaderXYZ sensorReaderXYZ = (SensorReaderXYZ) getSupportFragmentManager().findFragmentById(R.id.sensorXYZ);
        SensorReaderXYZ sensorReaderXYZ = (SensorReaderXYZ) getSupportFragmentManager().findFragmentByTag("sensorView");
        View magnetGameBackground = findViewById(R.id.magnetGameBackground);
        accelerometer = (Sensor) sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        Button back = findViewById(R.id.backButton);
        back.setOnClickListener(view -> finish());

        listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                int xValue = transformFloatToInt(sensorEvent.values[0]);
                int yValue = transformFloatToInt(sensorEvent.values[1]);
                int zValue = transformFloatToInt(sensorEvent.values[2]);
//                sensorReaderXYZ.setXYZ(xValue,yValue,zValue);
                imageView.setRotationX(((sensorEvent.values[0] + 48) / 96 * 360) - 180);
                imageView.setRotationY(((sensorEvent.values[1] + 48) / 96 * 360) - 180);
                imageView.setRotation(((sensorEvent.values[2] + 48) / 96 * 360) - 180);
                magnetGameBackground.setBackgroundColor(Color.argb(255,xValue,yValue,zValue));
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };
    }
    int transformFloatToInt(float value){
        return (int) ((value + 48) / 96 *255);
    }

    @Override
    protected void onStart() {
        super.onStart();
        sensorManager.registerListener(listener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onStop() {
        sensorManager.unregisterListener(listener);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        sensorManager.unregisterListener(listener);
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(listener);
        super.onPause();
    }
}