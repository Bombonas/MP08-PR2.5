package com.example.mp08_pr25;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private Sensor sensor;
    SensorEventListener sensorListener;

    TextView textX, textY,textZ;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textX = (TextView)findViewById(R.id.x);
        textY = (TextView)findViewById(R.id.y);
        textZ = (TextView)findViewById(R.id.z);

        sensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                // Valors de l'acceleròmetre en m/s^2
                float xAcc = sensorEvent.values[0];
                float yAcc = sensorEvent.values[1];
                float zAcc = sensorEvent.values[2];

                // Processament o visualització de dades...
                textX.setText(String.format("X: %s", xAcc));
                textY.setText(String.format("Y: %s", yAcc));
                textZ.setText(String.format("Z: %s", zAcc));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
                // Es pot ignorar aquesta CB de moment
            }
        };

        // Seleccionem el tipus de sensor (veure doc oficial)
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // registrem el Listener per capturar els events del sensor
        if( sensor!=null ) {
            sensorManager.registerListener(sensorListener,sensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
}