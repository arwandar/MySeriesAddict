package com.arwandar.myseriesaddict.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.api.SharedPrefsSingleton;

import in.co.ophio.secure.core.ObscuredPreferencesBuilder;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashScreenActivity extends Activity {

    private final String SECRET_KEY = "bc7c3kk9a09ofjjeqeeufe9kbu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences sharedPreferences = new ObscuredPreferencesBuilder()
                .setApplication(getApplication())
                .obfuscateValue(true)
                .obfuscateKey(true)
                .setSharePrefFileName("shared_preferences_name")
                .setSecret(SECRET_KEY)
                .createSharedPrefs();
        SharedPrefsSingleton.initInstance(sharedPreferences);

        StartAnimations();

        int SPLASH_TIME_OUT = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
        return super.onTouchEvent(event);
    }

    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        FrameLayout l = (FrameLayout) findViewById(R.id.splash_layout);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        TextView iv = (TextView) findViewById(R.id.fullscreen_content);
        iv.clearAnimation();
        iv.startAnimation(anim);
    }
}
