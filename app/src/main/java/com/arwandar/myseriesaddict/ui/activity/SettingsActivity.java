package com.arwandar.myseriesaddict.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.api.SharedPrefsSingleton;

import butterknife.Bind;
import butterknife.OnClick;

public class SettingsActivity extends CustomActivity {

    @Bind(R.id.settings_nb_episodes)
    TextView mNbEpisodes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initActivity();
        int test = SharedPrefsSingleton.getEpisodesLimit();
        mNbEpisodes.setText(String.valueOf(SharedPrefsSingleton.getEpisodesLimit()));
    }

    @OnClick(R.id.submit_button)
    public void save() {
        int test = Integer.parseInt(mNbEpisodes.getText().toString());
        SharedPrefsSingleton.setEpisodesLimit(test);
    }
}
