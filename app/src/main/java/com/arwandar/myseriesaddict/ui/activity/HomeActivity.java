package com.arwandar.myseriesaddict.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.common.util.SharedPrefsSingleton;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.home_friends)
    public void startFriendsActivity(View view) {
        Intent intent = new Intent(HomeActivity.this, FriendsActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.home_archived_shows)
    public void startPendingShowsActivity() {
        //TODO
        Intent intent = new Intent(HomeActivity.this, ShowsActivity.class);
        //intent.putExtra("listSelected", );
        startActivity(intent);
    }

    @OnClick(R.id.home_archived_shows)
    public void startArchivedShowsActivity() {
        //TODO
        Intent intent = new Intent(HomeActivity.this, ShowsActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.home_episodes)
    public void startEpisodesActivity() {
        //TODO
        Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.home_deconnexion)
    public void startDeconnection() {
        SharedPrefsSingleton.setAccessToken("");
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
