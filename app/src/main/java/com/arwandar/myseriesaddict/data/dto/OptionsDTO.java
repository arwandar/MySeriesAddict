package com.arwandar.myseriesaddict.data.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by olivi on 05/05/2016.
 */
public class OptionsDTO {

    @SerializedName("timelag")
    private String mTimelag;
    @SerializedName("global")
    private String mGlobal;
    @SerializedName("friendship")
    private String mFriendship;
    @SerializedName("downloaded")
    private String mDownloaded;
    @SerializedName("notation")
    private String mNotation;

    public String getmTimelag() {
        return mTimelag;
    }

    public String getmGlobal() {
        return mGlobal;
    }

    public String getmFriendship() {
        return mFriendship;
    }

    public String getmDownloaded() {
        return mDownloaded;
    }

    public String getmNotation() {
        return mNotation;
    }
}
