package com.arwandar.myseriesaddict.api.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by olivi on 04/05/2016.
 */
public class Episode implements Serializable {
    private List<String> mSubtitles;
    private String mEpisode;
    private String mThetvdbid;
    private String mGlobal;
    private Shows mShow;
    private String mYoutubeId;
    private String mCode;
    private String mDate;
    private String mId;
    private String mTitle;
    private String mSeason;
    private String mDescription;
    private String mSpecial;
    private ShowsUser mUser;
    private String mComments;
    private Notes mNote;

    public List<String> getmSubtitles() {
        return mSubtitles;
    }

    public void setmSubtitles(List<String> mSubtitles) {
        this.mSubtitles = mSubtitles;
    }

    public String getmEpisode() {
        return mEpisode;
    }

    public void setmEpisode(String mEpisode) {
        this.mEpisode = mEpisode;
    }

    public String getmThetvdbid() {
        return mThetvdbid;
    }

    public void setmThetvdbid(String mThetvdbid) {
        this.mThetvdbid = mThetvdbid;
    }

    public String getmGlobal() {
        return mGlobal;
    }

    public void setmGlobal(String mGlobal) {
        this.mGlobal = mGlobal;
    }

    public Shows getmShow() {
        return mShow;
    }

    public void setmShow(Shows show) {
        this.mShow = show;
    }

    public String getmYoutubeId() {
        return mYoutubeId;
    }

    public void setmYoutubeId(String mYoutubeId) {
        this.mYoutubeId = mYoutubeId;
    }

    public String getmCode() {
        return mCode;
    }

    public void setmCode(String mCode) {
        this.mCode = mCode;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmSeason() {
        return mSeason;
    }

    public void setmSeason(String mSeason) {
        this.mSeason = mSeason;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmSpecial() {
        return mSpecial;
    }

    public void setmSpecial(String mSpecial) {
        this.mSpecial = mSpecial;
    }

    public ShowsUser getmUser() {
        return mUser;
    }

    public void setmUser(ShowsUser mUser) {
        this.mUser = mUser;
    }

    public String getmComments() {
        return mComments;
    }

    public void setmComments(String mComments) {
        this.mComments = mComments;
    }

    public Notes getmNote() {
        return mNote;
    }

    public void setmNote(Notes mNote) {
        this.mNote = mNote;
    }
}
