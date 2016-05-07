package com.arwandar.myseriesaddict.data.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

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
