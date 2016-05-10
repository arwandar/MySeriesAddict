package com.arwandar.myseriesaddict.api;

import android.content.Context;
import android.content.SharedPreferences;

import com.arwandar.myseriesaddict.ui.SecurePreferences;

/**
 * Created by Arwandar on 05/05/2016.
 */
public class SharedPrefsSingleton {

    private static SharedPrefsSingleton instance;

    public SecurePreferences prefs;

    private SharedPrefsSingleton(Context context) {
        prefs = new SecurePreferences(context, "preferencesMyBetaSeries", "KHjiO4Mp!Oi4542A", true);

        prefs.put("url", "https://www.betaseries.com/authorize");
        prefs.put("clientId", "a93691358c05");
        prefs.put("clientSecret", "17d90f0c382e7623a09c6c29d3519028");
        prefs.put("version", "2.4");
        prefs.put("redirectUri", "http://127.0.0.1");
        prefs.put("baseUrl", "https://api.betaseries.com");
    }

    public static void initInstance(SharedPreferences prefs, Context context) {
        if (instance == null) {
            instance = new SharedPrefsSingleton(context);
        }
    }

    public static SharedPrefsSingleton getInstance() {
        return instance;
    }

    public static String getVersion() {
        return instance.prefs.getString("version", "");
    }

    public static String getUrl() {
        return instance.prefs.getString("url", "");
    }

    public static String getClientSecret() {
        return instance.prefs.getString("clientSecret", "");
    }

    public static String getClientId() {
        return instance.prefs.getString("clientId", "");
    }

    public static String getRedirectURI() {
        return instance.prefs.getString("redirectUri", "");
    }

    public static String getAccessToken() {
        return instance.prefs.getString("accessToken", "");
    }

    public static void setAccessToken(String pAccessToken) {
        instance.prefs.put("accessToken", pAccessToken);
    }

    public static String getBaseUrl() {
        return instance.prefs.getString("baseUrl", "");
    }

    public static int getEpisodesLimit() {
        return instance.prefs.getInt("episodesLimit", 2);
    }

    public static void setEpisodesLimit(int pLimit) {
        instance.prefs.putInt("episodesLimit", pLimit);
    }
}
