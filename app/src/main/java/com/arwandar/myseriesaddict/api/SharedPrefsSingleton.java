package com.arwandar.myseriesaddict.api;

import android.content.SharedPreferences;

/**
 * Created by Arwandar on 05/05/2016.
 */
public class SharedPrefsSingleton {

    private static SharedPrefsSingleton instance;

    public SharedPreferences prefs;

    private SharedPrefsSingleton(SharedPreferences prefs) {



        // Constructor hidden because this is a singleton
        this.prefs = prefs;
        SharedPreferences.Editor editor = this.prefs.edit();
        editor.putString("url", "https://www.betaseries.com/authorize");
        editor.putString("clientId", "a93691358c05");
        editor.putString("clientSecret", "17d90f0c382e7623a09c6c29d3519028");
        editor.putString("version", "2.4");
        editor.putString("redirectUri", "http://127.0.0.1");
        editor.putString("baseUrl", "https://api.betaseries.com");
        editor.commit();
    }

    public static void initInstance(SharedPreferences prefs) {
        if (instance == null) {
            instance = new SharedPrefsSingleton(prefs);
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
        SharedPreferences.Editor editor = instance.prefs.edit();
        editor.putString("accessToken", pAccessToken);
        editor.commit();
    }

    public static String getBaseUrl() {
        return instance.prefs.getString("baseUrl", "");
    }

    public static int getEpisodesLimit() {
        return instance.prefs.getInt("episodesLimit", 2);
    }

    public static void setEpisodesLimit(int pLimit) {
        SharedPreferences.Editor editor = instance.prefs.edit();

        editor.putInt("episodesLimit", pLimit);
        editor.commit();
    }
}