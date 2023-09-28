package dte.masteriot.mdp.Sensors01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    Button bLight, bAccel, bStep;
    TextView tvLightSensorValue, tvAccelSensorValue, tvStepSensorValue;
    private SensorManager lightSensorManager, accelSensorManager, stepSensorManager;
    private Sensor lightSensor, accelSensor, stepSensor;
    boolean lightSensorIsActive, accelSensorIsActive, stepSensorIsActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lightSensorIsActive = false;
        accelSensorIsActive = false;
        stepSensorIsActive = false;

        // Get the references to the UI:
        bLight = findViewById(R.id.bLight); // button to start/stop sensor's readings
        tvLightSensorValue = findViewById(R.id.lightMeasurement); // sensor's values

        bAccel = findViewById(R.id.bAccel);
        tvAccelSensorValue = findViewById(R.id.accelMeasurement);

        bStep = findViewById(R.id.bStep);
        tvStepSensorValue = findViewById(R.id.stepMeasurement);

        // Get the reference to the sensor manager and the sensor:
        lightSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        stepSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Obtain the reference to the default light sensor of the device:
        lightSensor = lightSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        accelSensor = accelSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        stepSensor = stepSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        // Listener for the light button:
        bLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lightSensorIsActive) {
                    // unregister listener and make the appropriate changes in the UI:
                    lightSensorManager.unregisterListener(MainActivity.this, lightSensor);
                    bLight.setText(R.string.light_sensor_off);
                    Drawable button_off =
                            ResourcesCompat.getDrawable(getResources(), R.drawable.round_button_off, null);
                    bLight.setBackground(button_off);
                    tvLightSensorValue.setText("Light sensor is OFF");
                    lightSensorIsActive = false;
                } else {
                    // register listener and make the appropriate changes in the UI:
                    lightSensorManager.registerListener(MainActivity.this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
                    bLight.setText(R.string.light_sensor_on);
                    Drawable button_on =
                            ResourcesCompat.getDrawable(getResources(), R.drawable.round_button_on, null);
                    bLight.setBackground(button_on);
                    tvLightSensorValue.setText("Waiting for first light sensor value");
                    lightSensorIsActive = true;
                }
            }
        });

        // Listener for the accel button:
        bAccel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (accelSensorIsActive) {
                    // unregister listener and make the appropriate changes in the UI:
                    accelSensorManager.unregisterListener(MainActivity.this, accelSensor);
                    bAccel.setText(R.string.accel_sensor_off);
                    Drawable button_off =
                            ResourcesCompat.getDrawable(getResources(), R.drawable.round_button_off, null);
                    bAccel.setBackground(button_off);
                    tvAccelSensorValue.setText("Acceleration sensor is OFF");
                    accelSensorIsActive = false;
                } else {
                    // register listener and make the appropriate changes in the UI:
                    accelSensorManager.registerListener(MainActivity.this, accelSensor, SensorManager.SENSOR_DELAY_NORMAL);
                    bAccel.setText(R.string.accel_sensor_on);
                    Drawable button_on =
                            ResourcesCompat.getDrawable(getResources(), R.drawable.round_button_on, null);
                    bAccel.setBackground(button_on);
                    tvAccelSensorValue.setText("Waiting for first accel sensor value");
                    accelSensorIsActive = true;
                }
            }
        });

        // Listener for the step button:
        bLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lightSensorIsActive) {
                    // unregister listener and make the appropriate changes in the UI:
                    lightSensorManager.unregisterListener(MainActivity.this, lightSensor);
                    bLight.setText(R.string.light_sensor_off);
                    Drawable button_off =
                            ResourcesCompat.getDrawable(getResources(), R.drawable.round_button_off, null);
                    bLight.setBackground(button_off);
                    tvLightSensorValue.setText("Light sensor is OFF");
                    lightSensorIsActive = false;
                } else {
                    // register listener and make the appropriate changes in the UI:
                    lightSensorManager.registerListener(MainActivity.this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
                    bLight.setText(R.string.light_sensor_on);
                    Drawable button_on =
                            ResourcesCompat.getDrawable(getResources(), R.drawable.round_button_on, null);
                    bLight.setBackground(button_on);
                    tvLightSensorValue.setText("Waiting for first light sensor value");
                    lightSensorIsActive = true;
                }
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        lightSensorIsActive = sharedPref.getBoolean("lightSensorIsActive", false );
        if (lightSensorIsActive) {
            // unregister listener and make the appropriate changes in the UI:
            lightSensorManager.unregisterListener(MainActivity.this, lightSensor);
            bLight.setText(R.string.light_sensor_off);
            Drawable button_off =
                    ResourcesCompat.getDrawable(getResources(), R.drawable.round_button_off, null);
            bLight.setBackground(button_off);
            tvLightSensorValue.setText("Light sensor is OFF");
            lightSensorIsActive = false;
        } else {
            // register listener and make the appropriate changes in the UI:
            lightSensorManager.registerListener(MainActivity.this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
            bLight.setText(R.string.light_sensor_on);
            Drawable button_on =
                    ResourcesCompat.getDrawable(getResources(), R.drawable.round_button_on, null);
            bLight.setBackground(button_on);
            tvLightSensorValue.setText("Waiting for first light sensor value");
            lightSensorIsActive = true;
        }



        accelSensorIsActive = sharedPref.getBoolean("accelSensorIsActive", false);
        stepSensorIsActive = sharedPref.getBoolean("stepSensorIsActive", false);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("lightSensorIsActive", lightSensorIsActive);
        editor.putBoolean("accelSensorIsActive", accelSensorIsActive);
        editor.putBoolean("stepSensorIsActive", stepSensorIsActive);
        editor.commit();
    }


    // Methods related to the SensorEventListener interface:
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        // Check the sensor type and update the appropriate TextView
        if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
            tvLightSensorValue.setText(Float.toString(sensorEvent.values[0]));
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            tvAccelSensorValue.setText("Acceleration values:\n" +
                    "X: " + sensorEvent.values[0] + "\n" +
                    "Y: " + sensorEvent.values[1] + "\n" +
                    "Z: " + sensorEvent.values[2]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        // In this app we do nothing if sensor's accuracy changes
    }

}