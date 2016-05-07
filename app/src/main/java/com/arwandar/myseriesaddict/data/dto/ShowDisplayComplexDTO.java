package com.arwandar.myseriesaddict.data.dto;


import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by olivi on 07/05/2016.
 */
public class ShowDisplayComplexDTO {

    @SerializedName("show")
    public ShowsDTO mShow;
    @SerializedName("errors")
    public List<String> mErrors;

    public ShowsDTO getmShow() {
        return mShow;
    }

    public List<String> getmErrors() {
        return mErrors;
    }
}
