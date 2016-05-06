package com.arwandar.myseriesaddict.data.model;

import java.io.Serializable;

/**
 * Created by olivi on 07/04/2016.
 */
public class User implements Serializable {

    private long mId;
    private String mLogin;
    private boolean mInAccount;
    //Ci-dessous, propriétés pour les informations d'un membre
    private String mCached;
    private Stats mStats;
    private String mXp;
    private String MProfileBbanner;
    private String mAvatar;
    private Options mOptions;

    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    public String getmLogin() {
        return mLogin;
    }

    public void setmLogin(String mLogin) {
        this.mLogin = mLogin;
    }

    public boolean ismInAccount() {
        return mInAccount;
    }

    public void setmInAccount(boolean mInAccount) {
        this.mInAccount = mInAccount;
    }

    public String getmCached() {
        return mCached;
    }

    public void setmCached(String mCached) {
        this.mCached = mCached;
    }

    public Stats getmStats() {
        return mStats;
    }

    public void setmStats(Stats mStats) {
        this.mStats = mStats;
    }

    public String getmXp() {
        return mXp;
    }

    public void setmXp(String mXp) {
        this.mXp = mXp;
    }

    public String getMProfileBbanner() {
        return MProfileBbanner;
    }

    public void setMProfileBbanner(String MProfileBbanner) {
        this.MProfileBbanner = MProfileBbanner;
    }

    public String getmAvatar() {
        return mAvatar;
    }

    public void setmAvatar(String mAvatar) {
        this.mAvatar = mAvatar;
    }

    public Options getmOptions() {
        return mOptions;
    }

    public void setmOptions(Options mOptions) {
        this.mOptions = mOptions;
    }
}
