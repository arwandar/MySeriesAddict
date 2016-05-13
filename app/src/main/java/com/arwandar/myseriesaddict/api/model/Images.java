package com.arwandar.myseriesaddict.api.model;

import java.io.Serializable;

/**
 * Created by olivi on 17/04/2016.
 */
public class Images implements Serializable {

    private String mBox;
    private String mShow;
    private String mBanner;
    private String mPoster;

    public String getmBox() {
        return mBox;
    }

    public void setmBox(String mBox) {
        this.mBox = mBox;
    }

    public String getmShow() {
        return mShow;
    }

    public void setmShow(String mShow) {
        this.mShow = mShow;
    }

    public String getmBanner() {
        return mBanner;
    }

    public void setmBanner(String mBanner) {
        this.mBanner = mBanner;
    }

    public String getmPoster() {
        return mPoster;
    }

    public void setmPoster(String mPoster) {
        this.mPoster = mPoster;
    }
}
