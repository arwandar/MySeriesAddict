package com.arwandar.myseriesaddict.api.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by olivi on 17/04/2016.
 */
public class ShowsComplex implements Serializable {

    private List<String> mErrors;
    private List<Shows> mShows;

    public List<String> getmErrors() {
        return mErrors;
    }

    public void setmErrors(List<String> mErrors) {
        this.mErrors = mErrors;
    }

    public List<Shows> getmShows() {
        return mShows;
    }

    public void setmShows(List<Shows> mShows) {
        this.mShows = mShows;
    }
}
