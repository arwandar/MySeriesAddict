package com.arwandar.myseriesaddict.ui.activity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.api.SharedPrefsSingleton;

import butterknife.Bind;
import butterknife.OnClick;

public class SettingsActivity extends CustomActivity {

    @Bind(R.id.settings_nb_episodes)
    TextView mNbEpisodes;
    @Bind(R.id.settings_seekBar)
    SeekBar mSeekBar;

    Integer cursorPadding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initActivity(-1);
        getContent();
        cursorPadding = getApplication().getResources().getInteger(R.integer
                .settings_cursor_max_limit) + getApplication().getResources().getInteger(R.integer
                .settings_cursor_offset);
        setCursor();

    }

    private void setCursor(){
        Integer prefsValue = Math.round(SharedPrefsSingleton.getShakeSensorAccuracy());
        mSeekBar.setProgress(cursorPadding - prefsValue);
    }

    /**
     * enregistrement des modifications
     */
    @OnClick(R.id.submit_button)
    protected void save() {
        SharedPrefsSingleton.setEpisodesLimit(Integer.parseInt(mNbEpisodes.getText().toString()));


        Integer computedValue = cursorPadding - mSeekBar.getProgress();
        SharedPrefsSingleton.setShakeSensorAccurcay((float) computedValue);
        Toast.makeText(SettingsActivity.this, R.string.save_modification_ok_message,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    void getContent() {
        mNbEpisodes.setText(String.valueOf(SharedPrefsSingleton.getEpisodesLimit()));
    }
}
