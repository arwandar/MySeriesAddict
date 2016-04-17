package com.arwandar.myseriesaddict.data.dto;

import com.arwandar.myseriesaddict.data.model.Shows;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by olivi on 17/04/2016.
 */
public class ShowsComplexDTO {

    @SerializedName("errors")
    private List<String> mErrors;
    @SerializedName("shows")
    private List<ShowsDTO> mShows;

    public List<String> getmErrors() {
        return mErrors;
    }

    public List<ShowsDTO> getmShows() {
        return mShows;
    }
}
