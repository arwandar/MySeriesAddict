package com.arwandar.myseriesaddict.ui.activity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.widget.Toast;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.ui.ShakeDetector;

/**
 * Created by Arwandar on 10/05/2016.
 */
public abstract class CustomShakableActivity extends CustomActivity {
    protected SensorManager mSensorManager;
    protected Sensor mAccelerometer;
    protected ShakeDetector mShakeDetector;

    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager
                .registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }

    @Override
    protected void initActivity() {
        super.initActivity();
        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
                /*
				 * The following method, "handleShakeEvent(count):" is a stub //
				 * method you would use to setup whatever you want done once the
				 * device has been shook.
				 */
                Toast.makeText(CustomShakableActivity.this,
                        R.string.shake_message, Toast.LENGTH_SHORT)
                        .show();
                getContent();
            }
        });
    }

    abstract void getContent();
}
