package com.arwandar.myseriesaddict.api.model;

import java.io.Serializable;

/**
 * Created by olivi on 17/04/2016.
 */
public class SeasonsDetails implements Serializable {

    private String mNumber;
    private String mEpisodes;

    public String getmNumber() {
        return mNumber;
    }

    public void setmNumber(String mNumber) {
        this.mNumber = mNumber;
    }

    public String getmEpisodes() {
        return mEpisodes;
    }

    public void setmEpisodes(String mEpisodes) {
        this.mEpisodes = mEpisodes;
    }
}
