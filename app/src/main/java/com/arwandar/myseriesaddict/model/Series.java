package com.arwandar.myseriesaddict.model;

/**
 * Created by Arwandar on 22/02/2016.
 */
public class Series {
    private String mName;
    private String mPlot;

    public boolean isEnded() {
        return mIsEnded;
    }

    private boolean mIsEnded;

    public String getmName() {
        return mName;
    }

    public String getmPlot() {
        return mPlot;
    }

    public Series(String mName, String mPlot, boolean mIsEnded) {
        this.mName = mName;
        this.mPlot = mPlot;
        this.mIsEnded = mIsEnded;
    }
}
