package com.arwandar.myseriesaddict.data.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by olivi on 17/04/2016.
 */
public class ShowsUserDTO {

    @SerializedName("tags")
    private String mTags;
    @SerializedName("remaining")
    private String mRemaining;
    @SerializedName("last")
    private String mLast;
    @SerializedName("archived")
    private String mArchived;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("favorited")
    private String mFavorited;

    public String getmTags() {
        return mTags;
    }

    public String getmRemaining() {
        return mRemaining;
    }

    public String getmLast() {
        return mLast;
    }

    public String getmArchived() {
        return mArchived;
    }

    public String getmStatus() {
        return mStatus;
    }

    public String getmFavorited() {
        return mFavorited;
    }
}
