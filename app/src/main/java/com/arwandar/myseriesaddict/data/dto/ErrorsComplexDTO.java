package com.arwandar.myseriesaddict.data.dto;

import com.arwandar.myseriesaddict.data.model.Errors;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by olivi on 07/05/2016.
 */
public class ErrorsComplexDTO {

    @SerializedName("errors")
    public List<Errors> mErrors;

    public List<Errors> getmErrors() {
        return mErrors;
    }
}
