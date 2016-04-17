package com.arwandar.myseriesaddict.data.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by olivi on 17/04/2016.
 */
public class NotesDTO {

    @SerializedName("total")
    private String mTotal;
    @SerializedName("mean")
    private String mMean;
    @SerializedName("user")
    private String mUser;

    public String getmTotal() {
        return mTotal;
    }

    public String getmMean() {
        return mMean;
    }

    public String getmUser() {
        return mUser;
    }
}
