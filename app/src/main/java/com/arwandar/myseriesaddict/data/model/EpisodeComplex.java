package com.arwandar.myseriesaddict.data.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by olivi on 04/05/2016.
 */
public class EpisodeComplex implements Serializable {
    private List<String> mErrors;
    private Episode mEpisode;

    public List<String> getmErrors() {
        return mErrors;
    }

    public void setmErrors(List<String> mErrors) {
        this.mErrors = mErrors;
    }

    public Episode getmEpisodes() {
        return mEpisode;
    }

    public void setmEpisodes(Episode mEpisode) {
        this.mEpisode = mEpisode;
    }
}
