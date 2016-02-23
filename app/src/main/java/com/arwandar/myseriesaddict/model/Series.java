package com.arwandar.myseriesaddict.model;

import android.graphics.Color;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by Arwandar on 22/02/2016.
 */
public class Series implements Serializable {
    private String mName;
    private String mPlot;
    private int mColor;
    private boolean mIsEnded;

    public Series(String mName, String mPlot, boolean mIsEnded) {
        this.mName = mName;
        this.mPlot = mPlot;
        this.mIsEnded = mIsEnded;

        Random rnd = new Random();
        this.mColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    public int getmColor() {
        return mColor;
    }

    public boolean isEnded() {
        return mIsEnded;
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
}
