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
import com.arwandar.myseriesaddict.api.SharedPrefsSingleton;
import com.arwandar.myseriesaddict.api.service.CallManager;

public class LoginActivity extends AppCompatActivity {

    /**
     * choix de l'activité à charger une fois logué
     */
    private Class<EpisodesListActivity> mActivityClass = EpisodesListActivity.class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (SharedPrefsSingleton.getAccessToken().isEmpty()) {

            WebView myWebView = (WebView) findViewById(R.id.webview);
            WebSettings webSettings = myWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            myWebView.setWebViewClient(new MyWebViewClient());

            StringBuilder builder = new StringBuilder();
            builder.append(SharedPrefsSingleton.getUrl());
            builder.append("?client_id=" + SharedPrefsSingleton.getClientId());
            builder.append("&version=" + SharedPrefsSingleton.getVersion());
            builder.append("&redirect_uri=" + SharedPrefsSingleton.getRedirectURI());

            myWebView.loadUrl(builder.toString());
        } else {
            Intent intent = new Intent(LoginActivity.this, mActivityClass);
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            if (url != null && url.startsWith(SharedPrefsSingleton.getRedirectURI())) {
                // This is my web site, so do not override; let my WebView load the page
                Uri uri = Uri.parse(url);
                String code = uri.getQueryParameter("code");
                if (code != null) {
                    CallManager.getAccessToken(code);
                    //redirection to homepage
                    Intent intent = new Intent(LoginActivity.this, mActivityClass);
                    startActivity(intent);
                } else if (uri.getQueryParameter("error") != null) {
                    // show an error message here
                }
                return false;
            }
            // Otherwise, the link is not for a page on my site,
            // so launch another Activity that handles URLs
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }
    }
}
