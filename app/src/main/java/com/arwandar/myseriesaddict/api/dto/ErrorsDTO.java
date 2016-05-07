package com.arwandar.myseriesaddict.api.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by olivi on 07/05/2016.
 */
public class ErrorsDTO {

    @SerializedName("text")
    private String mText;
    @SerializedName("code")
    private String mCode;

    public String getmText() {
        return mText;
    }

    public String getmCode() {
        return mCode;
    }
}
