package com.arwandar.myseriesaddict.data.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by olivi on 17/04/2016.
 */
public class Shows implements Serializable {

    private String mCreation;
    private List<String> mGenres;
    private String mThetvdbid;
    private List<SeasonsDetails> mSeasonsDetails;
    private String mEpisodes;
    private List<String> mAliases;
    private String mNetwork;
    private String mId;
    private String mFollowers;
    private String mTitle;
    private String mInAccount;
    private String mDescription;
    private String mLength;
    private String mStatus;
    private String mSimilars;
    private String mSeasons;
    private String mImdbid;
    private Images mImages;
    private String mRating;
    private String mLanguage;
    private String mResourceurl;
    private Notes mNotes;
    private ShowsUser mUser;
    private String mCharacters;
    private String mComments;
    //Ci-dessous, les propriétés pour épisodes
    private String mRemaining;
    private List<Unseen> mUnseen;

    public String getmCreation() {
        return mCreation;
    }

    public void setmCreation(String mCreation) {
        this.mCreation = mCreation;
    }

    public List<String> getmGenres() {
        return mGenres;
    }

    public void setmGenres(List<String> mGenres) {
        this.mGenres = mGenres;
    }

    public String getmThetvdbid() {
        return mThetvdbid;
    }

    public void setmThetvdbid(String mThetvdbid) {
        this.mThetvdbid = mThetvdbid;
    }

    public List<SeasonsDetails> getmSeasonsDetails() {
        return mSeasonsDetails;
    }

    public void setmSeasonsDetails(List<SeasonsDetails> mSeasonsDetails) {
        this.mSeasonsDetails = mSeasonsDetails;
    }

    public String getmEpisodes() {
        return mEpisodes;
    }

    public void setmEpisodes(String mEpisodes) {
        this.mEpisodes = mEpisodes;
    }

    public List<String> getmAliases() {
        return mAliases;
    }

    public void setmAliases(List<String> mAliases) {
        this.mAliases = mAliases;
    }

    public String getmNetwork() {
        return mNetwork;
    }

    public void setmNetwork(String mNetwork) {
        this.mNetwork = mNetwork;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmFollowers() {
        return mFollowers;
    }

    public void setmFollowers(String mFollowers) {
        this.mFollowers = mFollowers;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmInAccount() {
        return mInAccount;
    }

    public void setmInAccount(String mInAccount) {
        this.mInAccount = mInAccount;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmLength() {
        return mLength;
    }

    public void setmLength(String mLength) {
        this.mLength = mLength;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getmSimilars() {
        return mSimilars;
    }

    public void setmSimilars(String mSimilars) {
        this.mSimilars = mSimilars;
    }

    public String getmSeasons() {
        return mSeasons;
    }

    public void setmSeasons(String mSeasons) {
        this.mSeasons = mSeasons;
    }

    public String getmImdbid() {
        return mImdbid;
    }

    public void setmImdbid(String mImdbid) {
        this.mImdbid = mImdbid;
    }

    public Images getmImages() {
        return mImages;
    }

    public void setmImages(Images mImages) {
        this.mImages = mImages;
    }

    public String getmRating() {
        return mRating;
    }

    public void setmRating(String mRating) {
        this.mRating = mRating;
    }

    public String getmLanguage() {
        return mLanguage;
    }

    public void setmLanguage(String mLanguage) {
        this.mLanguage = mLanguage;
    }

    public String getmResourceurl() {
        return mResourceurl;
    }

    public void setmResourceurl(String mResourceurl) {
        this.mResourceurl = mResourceurl;
    }

    public Notes getmNotes() {
        return mNotes;
    }

    public void setmNotes(Notes mNotes) {
        this.mNotes = mNotes;
    }

    public ShowsUser getmUser() {
        return mUser;
    }

    public void setmUser(ShowsUser mUser) {
        this.mUser = mUser;
    }

    public String getmCharacters() {
        return mCharacters;
    }

    public void setmCharacters(String mCharacters) {
        this.mCharacters = mCharacters;
    }

    public String getmComments() {
        return mComments;
    }

    public void setmComments(String mComments) {
        this.mComments = mComments;
    }

    public String getmRemaining() {
        return mRemaining;
    }

    public void setmRemaining(String remaining) {
        this.mRemaining = remaining;
    }

    public List<Unseen> getmUnseen() {
        return mUnseen;
    }

    public void setmUnseen(List<Unseen> mUnseen) {
        this.mUnseen = mUnseen;
    }
}
