package com.arwandar.myseriesaddict.data.dto;

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

    public long getmId() {
        return mId;
    }

    public String getmLogin() {
        return mLogin;
    }

    public boolean ismInAccount() {
        return mInAccount;
    }
}
