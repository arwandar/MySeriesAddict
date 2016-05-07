package com.arwandar.myseriesaddict.api.model;

import java.io.Serializable;

/**
 * Created by olivi on 07/05/2016.
 */
public class Errors implements Serializable {

    private String mText;
    private String mCode;

    public String getmText() {
        return mText;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }

    public String getmCode() {
        return mCode;
    }

    public void setmCode(String mCode) {
        this.mCode = mCode;
    }
}
