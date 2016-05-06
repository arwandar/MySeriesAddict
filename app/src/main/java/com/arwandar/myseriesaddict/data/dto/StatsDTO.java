package com.arwandar.myseriesaddict.data.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by olivi on 05/05/2016.
 */
public class StatsDTO {

    @SerializedName("progress")
    private String mProgress;
    @SerializedName("badges")
    private String mBadges;
    @SerializedName("friends")
    private String mFriends;
    @SerializedName("seasons")
    private String mSeasons;
    @SerializedName("movies")
    private String mMovies;
    @SerializedName("episodes_to_watch")
    private String mEpisodesToWatch;
    @SerializedName("time_on_tv")
    private String mTimeOnTv;
    @SerializedName("time_to_spend")
    private String mTimeToSpend;
    @SerializedName("episodes")
    private String mEpisodes;
    @SerializedName("comments")
    private String mComments;
    @SerializedName("shows")
    private String mShows;

    public String getmProgress() {
        return mProgress;
    }

    public String getmBadges() {
        return mBadges;
    }

    public String getmFriends() {
        return mFriends;
    }

    public String getmSeasons() {
        return mSeasons;
    }

    public String getmMovies() {
        return mMovies;
    }

    public String getmEpisodesToWatch() {
        return mEpisodesToWatch;
    }

    public String getmTimeOnTv() {
        return mTimeOnTv;
    }

    public String getmTimeToSpend() {
        return mTimeToSpend;
    }

    public String getmEpisodes() {
        return mEpisodes;
    }

    public String getmComments() {
        return mComments;
    }

    public String getmShows() {
        return mShows;
    }
}
