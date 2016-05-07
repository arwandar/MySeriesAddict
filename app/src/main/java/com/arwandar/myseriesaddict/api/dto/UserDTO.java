package com.arwandar.myseriesaddict.api.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by olivi on 31/03/2016.
 */
public class UserDTO {

    @SerializedName("id")
    private long mId;
    @SerializedName("login")
    private String mLogin;
    @SerializedName("in_account")
    private boolean mInAccount;
    //Ci-dessous, propriétés pour les informations d'un membre
    @SerializedName("cached")
    private String mCached;
    @SerializedName("stats")
    private StatsDTO mStats;
    @SerializedName("xp")
    private String mXp;
    @SerializedName("profile_banner")
    private String MProfileBbanner;
    @SerializedName("avatar")
    private String mAvatar;
    @SerializedName("options")
    private OptionsDTO mOptions;


    public long getmId() {
        return mId;
    }

    public String getmLogin() {
        return mLogin;
    }

    public boolean ismInAccount() {
        return mInAccount;
    }

    public String getmCached() {
        return mCached;
    }

    public StatsDTO getmStats() {
        return mStats;
    }

    public String getmXp() {
        return mXp;
    }

    public String getMProfileBbanner() {
        return MProfileBbanner;
    }

    public String getmAvatar() {
        return mAvatar;
    }

    public OptionsDTO getmOptions() {
        return mOptions;
    }
}
