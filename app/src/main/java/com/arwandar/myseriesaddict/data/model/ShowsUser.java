package com.arwandar.myseriesaddict.data.model;

import java.io.Serializable;

/**
 * Created by olivi on 17/04/2016.
 */
public class ShowsUser implements Serializable {

    private String mTags;
    private String mRemaining;
    private String mLast;
    private String mArchived;
    private String mStatus;
    private String mFavorited;
    //Ci-dessous, les propriétés pour épisodes
    private String mDownloaded;
    private String mSeen;


    public String getmTags() {
        return mTags;
    }

    public void setmTags(String mTags) {
        this.mTags = mTags;
    }

    public String getmRemaining() {
        return mRemaining;
    }

    public void setmRemaining(String mRemaining) {
        this.mRemaining = mRemaining;
    }

    public String getmLast() {
        return mLast;
    }

    public void setmLast(String mLast) {
        this.mLast = mLast;
    }

    public String getmArchived() {
        return mArchived;
    }

    public void setmArchived(String mArchived) {
        this.mArchived = mArchived;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getmFavorited() {
        return mFavorited;
    }

    public void setmFavorited(String mFavorited) {
        this.mFavorited = mFavorited;
    }

    public String getmDownloaded() {
        return mDownloaded;
    }

    public void setmDownloaded(String mDownloaded) {
        this.mDownloaded = mDownloaded;
    }

    public String getmSeen() {
        return mSeen;
    }

    public void setmSeen(String mSeen) {
        this.mSeen = mSeen;
    }
}
