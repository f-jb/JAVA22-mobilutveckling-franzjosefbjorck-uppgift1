package com.example.shakerv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Button;
import android.widget.Spinner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent playAccelerometer = new Intent(this, AccelerometerGameActivity.class);
        Intent playMagnetGame = new Intent(this, MagnetGameActivity.class);
        Spinner gameChoice = findViewById(R.id.gameChoice);
        Button button = findViewById(R.id.button);




        /*
        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        PowerManager.OnThermalStatusChangedListener listener = new PowerManager.OnThermalStatusChangedListener() {
            @Override
            public void onThermalStatusChanged(int i) {

            }
        }


         */

        /*
        String[] sensorFiles = new String[] {
                "/sys/devices/system/cpu/cpu0/cpufreq/cpu_temp",
                "/sys/devices/system/cpu/cpu0/cpufreq/FakeShmoo_cpu_temp",
                "/sys/class/thermal/thermal_zone1/temp",
                "/sys/class/i2c-adapter/i2c-4/4-004c/temperature",
                "/sys/devices/platform/tegra-i2c.3/i2c-4/4-004c/temperature",
                "/sys/devices/platform/omap/omap_temp_sensor.0/temperature",
                "/sys/devices/platform/tegra_tmon/temp1_input",
                "/sys/kernel/debug/tegra_thermal/temp_tj",
                "/sys/devices/platform/s5p-tmu/temperature",
                "/sys/class/thermal/thermal_zone0/temp",
                "/sys/devices/virtual/thermal/thermal_zone0/temp",
                "/sys/class/hwmon/hwmon0/device/temp1_input",
                "/sys/devices/virtual/thermal/thermal_zone1/temp",
                "/sys/devices/platform/s5p-tmu/curr_temp"
        };
        File correctSensorFile = null;
        for (String file : sensorFiles) {
            File f = new File(file);
            if (f.exists()) {
                correctSensorFile = f;
                Log.d("Correct tempfile", correctSensorFile.getAbsolutePath());
                break;
            }
        }

        if (correctSensorFile == null)
            throw new RuntimeException("Did not find sensor file to read");

        RandomAccessFile reader = null;
        try {
            reader = new RandomAccessFile(correctSensorFile , "r");
            String value = reader.readLine();
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


         */

        button.setOnClickListener(view -> {
            String choice = gameChoice.getSelectedItem().toString();
            switch (choice){
                case "Magnetometer":
                    startActivity(playMagnetGame);
                    break;
                case "Accelerometer":
                    startActivity(playAccelerometer);
                break;
                default:
                    throw new IllegalStateException("Unexpected value: " + choice);
            }

        });


    }
}