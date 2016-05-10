package com.arwandar.myseriesaddict.api.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by olivi on 17/04/2016.
 */
public class ImagesDTO {

    @SerializedName("box")
    private String mBox;
    @SerializedName("show")
    private String mShow;
    @SerializedName("banner")
    private String mBanner;

    public String getmBox() {
        return mBox;
    }

    public String getmShow() {
        return mShow;
    }

    public String getmBanner() {
        return mBanner;
    }
}
