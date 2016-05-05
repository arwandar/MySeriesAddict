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
import com.arwandar.myseriesaddict.common.util.SharedPrefsSingleton;
import com.arwandar.myseriesaddict.data.service.CallManager;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (SharedPrefsSingleton.getAccessToken().isEmpty()) {

            WebView myWebView = (WebView) findViewById(R.id.webview);
            WebSettings webSettings = myWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            myWebView.setWebViewClient(new MyWebViewClient());
            //myWebView.loadUrl(url + "?client_id=" + clientId + "&version=" + version + "&redirect_uri=" + redirectUri);
            myWebView.loadUrl(SharedPrefsSingleton.getUrl() + "?client_id="
                    + SharedPrefsSingleton.getClientId() + "&version="
                    + SharedPrefsSingleton.getVersion() + "&redirect_uri="
                    + SharedPrefsSingleton.getRedirectURI());
        } else {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
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
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            if (url != null && url.startsWith(SharedPrefsSingleton.getRedirectURI())) {
                // This is my web site, so do not override; let my WebView load the page
                Uri uri = Uri.parse(url);
                String code = uri.getQueryParameter("code");
                if (code != null) {
                    CallManager.getAccessToken(code);
                    //redirection to homepage
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);

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
