package com.arwandar.myseriesaddict.model.old;

import android.graphics.Color;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Arwandar on 22/02/2016.
 */
public class Series implements Serializable, Comparable {
    private String mName;
    private String mPlot;
    private int mColor;

    public Series(String mName, String mPlot) {
        this.mName = mName;
        this.mPlot = mPlot;

        Random rnd = new Random();
        this.mColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    public static List<Series> buildListFromJSONArray(List<Series> seriesList, JSONArray jsonArray, String filter) {
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject seriesJson = jsonArray.getJSONObject(i);
                if (seriesJson.getString("status").equals(filter)) {
                    Series series = new Series(seriesJson.getString("title"), seriesJson.getString("description"));
                    //series.setImageSrc(new URL(showJson.getString("urlPoster")));
                    //show.setImdbURL(new URL(showJson.getString("urlIMDB")));
                    seriesList.add(series);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(seriesList);
        return seriesList;
    }

    public int getmColor() {
        return mColor;
    }

    public String getmName() {
        return mName;
    }

    public String getmPlot() {
        return mPlot;
    }

    public String getSmallName() {
        return this.mName.length() < 80 ? this.mName : this.mName.substring(0, 77).concat("...");
    }

    public String getSmallPlot() {
        return this.mPlot.length() < 80 ? this.mPlot : this.mPlot.substring(0, 77).concat("...");
    }

    @Override
    public int compareTo(Object another) {
        return this.getmName().compareTo(((Series) another).getmName());
    }
}
