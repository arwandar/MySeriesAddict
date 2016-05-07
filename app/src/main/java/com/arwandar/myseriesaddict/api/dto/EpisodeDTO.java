package com.arwandar.myseriesaddict.api.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by olivi on 04/05/2016.
 */
public class EpisodeDTO {
    @SerializedName("subtitles")
    private List<String> mSubtitles;
    @SerializedName("episode")
    private String mEpisode;
    @SerializedName("thetvdb_id")
    private String mThetvdbid;
    @SerializedName("global")
    private String mGlobal;
    @SerializedName("show")
    private ShowsDTO mShow;
    @SerializedName("youtube_id")
    private String mYoutubeId;
    @SerializedName("code")
    private String mCode;
    @SerializedName("date")
    private String mDate;
    @SerializedName("id")
    private String mId;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("season")
    private String mSeason;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("special")
    private String mSpecial;
    @SerializedName("user")
    private ShowsUserDTO mUser;
    @SerializedName("comments")
    private String mComments;
    @SerializedName("note")
    private NotesDTO mNote;

    public List<String> getmSubtitles() {
        return mSubtitles;
    }

    public String getmEpisode() {
        return mEpisode;
    }

    public String getmThetvdbid() {
        return mThetvdbid;
    }

    public String getmGlobal() {
        return mGlobal;
    }

    public ShowsDTO getmShow() {
        return mShow;
    }

    public String getmYoutubeId() {
        return mYoutubeId;
    }

    public String getmCode() {
        return mCode;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmSeason() {
        return mSeason;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmSpecial() {
        return mSpecial;
    }

    public ShowsUserDTO getmUser() {
        return mUser;
    }

    public String getmComments() {
        return mComments;
    }

    public NotesDTO getmNote() {
        return mNote;
    }
}
