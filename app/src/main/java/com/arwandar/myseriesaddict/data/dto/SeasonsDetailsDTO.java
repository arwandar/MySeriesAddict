package com.arwandar.myseriesaddict.data.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by olivi on 17/04/2016.
 */
public class SeasonsDetailsDTO {

    @SerializedName("number")
    private String mNumber;
    @SerializedName("episodes")
    private String mEpisodes;

    public String getmNumber() {
        return mNumber;
    }

    public String getmEpisodes() {
        return mEpisodes;
    }
}
