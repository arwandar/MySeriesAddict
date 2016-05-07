package com.arwandar.myseriesaddict.api.model;

import java.io.Serializable;

/**
 * Created by olivi on 05/05/2016.
 */
public class Stats implements Serializable {

    private String mProgress;
    private String mBadges;
    private String mFriends;
    private String mSeasons;
    private String mMovies;
    private String mEpisodesToWatch;
    private String mTimeOnTv;
    private String mTimeToSpend;
    private String mEpisodes;
    private String mComments;
    private String mShows;

    public String getmProgress() {
        return mProgress;
    }

    public void setmProgress(String mProgress) {
        this.mProgress = mProgress;
    }

    public String getmBadges() {
        return mBadges;
    }

    public void setmBadges(String mBadges) {
        this.mBadges = mBadges;
    }

    public String getmFriends() {
        return mFriends;
    }

    public void setmFriends(String mFriends) {
        this.mFriends = mFriends;
    }

    public String getmSeasons() {
        return mSeasons;
    }

    public void setmSeasons(String mSeasons) {
        this.mSeasons = mSeasons;
    }

    public String getmMovies() {
        return mMovies;
    }

    public void setmMovies(String mMovies) {
        this.mMovies = mMovies;
    }

    public String getmEpisodesToWatch() {
        return mEpisodesToWatch;
    }

    public void setmEpisodesToWatch(String mEpisodesToWatch) {
        this.mEpisodesToWatch = mEpisodesToWatch;
    }

    public String getmTimeOnTv() {
        return mTimeOnTv;
    }

    public void setmTimeOnTv(String mTimeOnTv) {
        this.mTimeOnTv = mTimeOnTv;
    }

    public String getmTimeToSpend() {
        return mTimeToSpend;
    }

    public void setmTimeToSpend(String mTimeToSpend) {
        this.mTimeToSpend = mTimeToSpend;
    }

    public String getmEpisodes() {
        return mEpisodes;
    }

    public void setmEpisodes(String mEpisodes) {
        this.mEpisodes = mEpisodes;
    }

    public String getmComments() {
        return mComments;
    }

    public void setmComments(String mComments) {
        this.mComments = mComments;
    }

    public String getmShows() {
        return mShows;
    }

    public void setmShows(String mShows) {
        this.mShows = mShows;
    }
}
