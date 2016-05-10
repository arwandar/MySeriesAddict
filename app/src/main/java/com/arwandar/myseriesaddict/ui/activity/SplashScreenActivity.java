package com.arwandar.myseriesaddict.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.api.SharedPrefsSingleton;

import in.co.ophio.secure.core.ObscuredPreferencesBuilder;



/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashScreenActivity extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences sharedPreferences = new ObscuredPreferencesBuilder()
                .setApplication(getApplication())
                .obfuscateValue(true)
                .obfuscateKey(true)
                .setSharePrefFileName("shared_preferences_name")
                .setSecret("chat")     //secret key
                .createSharedPrefs();
        SharedPrefsSingleton.initInstance(sharedPreferences);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}
