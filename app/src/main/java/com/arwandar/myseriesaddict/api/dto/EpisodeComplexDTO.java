package com.arwandar.myseriesaddict.api.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by olivi on 04/05/2016.
 */
public class EpisodeComplexDTO {
    @SerializedName("errors")
    private List<String> mErrors;
    @SerializedName("episode")
    private EpisodeDTO mEpisode;

    public List<String> getmErrors() {
        return mErrors;
    }

    public EpisodeDTO getmEpisode() {
        return mEpisode;
    }
}
