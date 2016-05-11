package com.arwandar.myseriesaddict.api.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by olivi on 11/05/2016.
 */
public class EpisodesComplexDTO {
    @SerializedName("errors")
    private List<String> mErrors;
    @SerializedName("episodes")
    private List<EpisodeDTO> mEpisodes;

    public List<String> getmErrors() {
        return mErrors;
    }

    public List<EpisodeDTO> getmEpisodes() {
        return mEpisodes;
    }
}
