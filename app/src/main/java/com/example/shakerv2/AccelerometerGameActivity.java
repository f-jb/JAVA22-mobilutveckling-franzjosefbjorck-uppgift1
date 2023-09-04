package com.example.shakerv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class AccelerometerGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer_game);

        Button back = findViewById(R.id.backButton);
        Spinner difficulty = findViewById(R.id.gameDifficulty);

        View accelerometerBackground = findViewById(R.id.accelerometerBackground);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor accelerometer = (Sensor) sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragmentContainerView, SensorReaderXYZ.class, null, "sensorView")
                .commit();
        SensorEventListener listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                int gameRatio =  Integer.valueOf(difficulty.getSelectedItem().toString());

                int xValue =  (int) Math.abs(sensorEvent.values[0]) * gameRatio;
                int yValue = (int) Math.abs(sensorEvent.values[1]) * gameRatio;
                int zValue = (int) Math.abs(sensorEvent.values[2]) * gameRatio;
                int totalValue = xValue + yValue + zValue;
                SensorReaderXYZ sensorReaderXYZ = (SensorReaderXYZ) fragmentManager.findFragmentByTag("sensorView");
                sensorReaderXYZ.setXYZ(xValue,yValue,zValue);

                if(totalValue >= 100 ){
                    Toast.makeText(AccelerometerGameActivity.this, "WOw! So much fastness", Toast.LENGTH_LONG).show();
                }
                accelerometerBackground.setBackgroundColor(Color.argb(255, totalValue, totalValue, totalValue));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        sensorManager.registerListener(listener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        back.setOnClickListener(view -> finish());
    }
    /*
    int transformFloatToInt(float value){
        return (int) ((value + 48) / 96 *255);
    }

     */
}