package com.arwandar.myseriesaddict.model;

import java.io.Serializable;

/**
 * Created by olivi on 07/04/2016.
 */
public class User implements Serializable {

    private long mId;

    private String mLogin;

    private boolean mInAccount;

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
}
