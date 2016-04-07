package com.arwandar.myseriesaddict.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.data.AccessToken;
import com.arwandar.myseriesaddict.data.ServiceGenerator;
import com.arwandar.myseriesaddict.data.service.ILoginService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private final String url = "https://www.betaseries.com/authorize";
    private final String clientId = "a93691358c05";
    private final String clientSecret = "17d90f0c382e7623a09c6c29d3519028";
    private final String version = "2.4";
    private final String redirectUri = "http://127.0.0.1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Button loginButton = (Button) findViewById(R.id.loginbutton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(url + "?client_id=" + clientId + "&version=" + version + "&redirect_uri=" + redirectUri));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // the intent filter defined in AndroidManifest will handle the return from ACTION_VIEW intent
        Uri uri = getIntent().getData();
        if (uri != null && uri.toString().startsWith(redirectUri)) {
            // use the parameter your API exposes for the code (mostly it's "code")
            String code = uri.getQueryParameter("code");
            if (code != null) {
                // get access token
                ILoginService loginService = ServiceGenerator.createService(ILoginService.class, null);
                Call<AccessToken> call = loginService.getAccessToken(code, "authorization_code", clientSecret, redirectUri, clientId, version);

                call.enqueue(new Callback<AccessToken>() {
                    @Override
                    public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                        if (response.isSuccessful()) {
                            System.out.println(response);
                            // tasks available
                        } else {
                            // error response, no access to resource?
                        }
                    }

                    @Override
                    public void onFailure(Call<AccessToken> call, Throwable t) {
                        // something went completely south (like no internet connection)
                        Log.d("Error", t.getMessage());
                    }
                });

                    //try {
                //    AccessToken accessToken = call.execute().body();
                //    System.out.println(accessToken.toString());
                //} catch (Exception e) {
                //    e.printStackTrace();
                //}
            } else if (uri.getQueryParameter("error") != null) {
                // show an error message here
            }
        }
    }
}
