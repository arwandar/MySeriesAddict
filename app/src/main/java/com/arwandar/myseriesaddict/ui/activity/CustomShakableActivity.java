package com.arwandar.myseriesaddict.ui.activity;

import android.hardware.Sensor;
import android.hardware.SensorManager;

/**
 * Created by Arwandar on 10/05/2016.
 */
public class CustomShakableActivity extends CustomActivity {

    protected SensorManager mSensorManager;
    protected Sensor mAccelerometer;
    protected ShakeDetector mShakeDetector;
}
