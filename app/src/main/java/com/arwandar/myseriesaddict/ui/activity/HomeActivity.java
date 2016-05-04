package com.arwandar.myseriesaddict.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.arwandar.myseriesaddict.R;

public class HomeActivity extends AppCompatActivity {
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        Button episodes = (Button) findViewById(R.id.home_episodes);
        episodes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO demarrer la bonne activité
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        Button pendingShows = (Button) findViewById(R.id.home_pending_shows);
        pendingShows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO passer les bons parametres
                Intent intent = new Intent(HomeActivity.this, ShowsListActivity.class);
                startActivity(intent);
            }
        });

        Button archiveButton = (Button) findViewById(R.id.home_archived_shows);
        archiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO passer les bons parametres
                Intent intent = new Intent(HomeActivity.this, ShowsListActivity.class);
                startActivity(intent);
            }
        });

        Button friendsButton = (Button) findViewById(R.id.home_friends);
        friendsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO passer les bons parametres
                Intent intent = new Intent(HomeActivity.this, FriendsActivity.class);
                startActivity(intent);
            }
        });

        Button deconnexionButton = (Button) findViewById(R.id.home_deconnexion);
        deconnexionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.remove("accessToken");
                editor.commit();
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
