package com.arwandar.myseriesaddict.api.model;

import java.io.Serializable;

/**
 * Created by olivi on 05/05/2016.
 */
public class Options implements Serializable {

    private String mTimelag;
    private String mGlobal;
    private String mFriendship;
    private String mDownloaded;
    private String mNotation;

    public String getmTimelag() {
        return mTimelag;
    }

    public void setmTimelag(String mTimelag) {
        this.mTimelag = mTimelag;
    }

    public String getmGlobal() {
        return mGlobal;
    }

    public void setmGlobal(String mGlobal) {
        this.mGlobal = mGlobal;
    }

    public String getmFriendship() {
        return mFriendship;
    }

    public void setmFriendship(String mFriendship) {
        this.mFriendship = mFriendship;
    }

    public String getmDownloaded() {
        return mDownloaded;
    }

    public void setmDownloaded(String mDownloaded) {
        this.mDownloaded = mDownloaded;
    }

    public String getmNotation() {
        return mNotation;
    }

    public void setmNotation(String mNotation) {
        this.mNotation = mNotation;
    }
}
