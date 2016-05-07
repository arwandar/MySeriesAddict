package com.arwandar.myseriesaddict.api.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by olivi on 17/04/2016.
 */
public class ShowsDTO {

    @SerializedName("creation")
    private String mCreation;
    @SerializedName("genres")
    private List<String> mGenres;
    @SerializedName("thetvdb_id")
    private String mThetvdbid;
    @SerializedName("seasons_details")
    private List<SeasonsDetailsDTO> mSeasonsDetails;
    @SerializedName("episodes")
    private String mEpisodes;
    @SerializedName("aliases")
    private List<String> mAliases;
    @SerializedName("network")
    private String mNetwork;
    @SerializedName("id")
    private String mId;
    @SerializedName("followers")
    private String mFollowers;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("in_account")
    private String mInAccount;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("length")
    private String mLength;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("similars")
    private String mSimilars;
    @SerializedName("seasons")
    private String mSeasons;
    @SerializedName("imdb_id")
    private String mImdbid;
    @SerializedName("images")
    private ImagesDTO mImages;
    @SerializedName("rating")
    private String mRating;
    @SerializedName("language")
    private String mLanguage;
    @SerializedName("resource_url")
    private String mResourceurl;
    @SerializedName("notes")
    private NotesDTO mNotes;
    @SerializedName("user")
    private ShowsUserDTO mUser;
    @SerializedName("characters")
    private String mCharacters;
    @SerializedName("comments")
    private String mComments;
    //Ci-dessous, les propriétés pour épisodes
    @SerializedName("remaining")
    private String mRemaining;
    @SerializedName("unseen")
    private List<UnseenDTO> mUnseen;

    public String getmCreation() {
        return mCreation;
    }

    public List<String> getmGenres() {
        return mGenres;
    }

    public String getmThetvdbid() {
        return mThetvdbid;
    }

    public List<SeasonsDetailsDTO> getmSeasonsDetails() {
        return mSeasonsDetails;
    }

    public String getmEpisodes() {
        return mEpisodes;
    }

    public List<String> getmAliases() {
        return mAliases;
    }

    public String getmNetwork() {
        return mNetwork;
    }

    public String getmId() {
        return mId;
    }

    public String getmFollowers() {
        return mFollowers;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmInAccount() {
        return mInAccount;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmLength() {
        return mLength;
    }

    public String getmStatus() {
        return mStatus;
    }

    public String getmSimilars() {
        return mSimilars;
    }

    public String getmSeasons() {
        return mSeasons;
    }

    public String getmImdbid() {
        return mImdbid;
    }

    public ImagesDTO getmImages() {
        return mImages;
    }

    public String getmRating() {
        return mRating;
    }

    public String getmLanguage() {
        return mLanguage;
    }

    public String getmResourceurl() {
        return mResourceurl;
    }

    public NotesDTO getmNotes() {
        return mNotes;
    }

    public ShowsUserDTO getmUser() {
        return mUser;
    }

    public String getmCharacters() {
        return mCharacters;
    }

    public String getmComments() {
        return mComments;
    }

    public String getmRemaining() {
        return mRemaining;
    }

    public List<UnseenDTO> getmUnseen() {
        return mUnseen;
    }
}
