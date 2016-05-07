package com.arwandar.myseriesaddict.api.model;

import java.io.Serializable;

/**
 * Created by olivi on 17/04/2016.
 */
public class Notes implements Serializable {

    private String mTotal;
    private String mMean;
    private String mUser;

    public String getmTotal() {
        return mTotal;
    }

    public void setmTotal(String mTotal) {
        this.mTotal = mTotal;
    }

    public String getmMean() {
        return mMean;
    }

    public void setmMean(String mMean) {
        this.mMean = mMean;
    }

    public String getmUser() {
        return mUser;
    }

    public void setmUser(String mUser) {
        this.mUser = mUser;
    }
}
