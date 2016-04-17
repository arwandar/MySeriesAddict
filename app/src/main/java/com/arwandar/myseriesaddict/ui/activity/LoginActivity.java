package com.arwandar.myseriesaddict.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.data.model.ShowsComplex;
import com.arwandar.myseriesaddict.data.service.CallManager;
import com.arwandar.myseriesaddict.data.model.Users;

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

        WebView myWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new MyWebViewClient());
        myWebView.loadUrl(url + "?client_id=" + clientId + "&version=" + version + "&redirect_uri=" + redirectUri);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            if (url != null && url.startsWith(redirectUri)) {
                // This is my web site, so do not override; let my WebView load the page
                Uri uri = Uri.parse(url);
                String code = uri.getQueryParameter("code");
                if (code != null) {
                    // get access token
                   CallManager.getAccessToken(code, clientSecret, redirectUri, clientId, version);

                    //Services tests
                    Users fList = CallManager.getFriendsList();
                    ShowsComplex favs = CallManager.getFavoritesShows();
                    System.out.println("Here comes the test comment");





                    //ToDo: redirect to activity

                } else if (uri.getQueryParameter("error") != null) {
                    // show an error message here
                }
                return false;
            }
            // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }

    }
}
