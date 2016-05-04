package com.arwandar.myseriesaddict.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.arwandar.myseriesaddict.R;

public class FriendsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        // get access token
        //CallManager.getAccessToken(code, clientSecret, redirectUri, clientId, version);

        //Services tests
        //Users fList = CallManager.getFriendsList();
        //System.out.println("Here comes the test comment");

    }
}
