package com.arwandar.myseriesaddict.api.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by olivi on 11/05/2016.
 */
public class EpisodesComplex implements Serializable {

    private List<String> mErrors;
    private List<Episode> mEpisodes;

    public List<String> getmErrors() {
        return mErrors;
    }

    public void setmErrors(List<String> mErrors) {
        this.mErrors = mErrors;
    }

    public List<Episode> getmEpisodes() {
        return mEpisodes;
    }

    public void setmEpisodes(List<Episode> mEpisodes) {
        this.mEpisodes = mEpisodes;
    }
}
