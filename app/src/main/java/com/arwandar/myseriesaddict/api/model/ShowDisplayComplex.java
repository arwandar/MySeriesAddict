package com.arwandar.myseriesaddict.api.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by olivi on 07/05/2016.
 */
public class ShowDisplayComplex implements Serializable {

    public Shows mShow;
    public List<String> mErrors;

    public Shows getmShow() {
        return mShow;
    }

    public void setmShow(Shows mShow) {
        this.mShow = mShow;
    }

    public List<String> getmErrors() {
        return mErrors;
    }

    public void setmErrors(List<String> mErrors) {
        this.mErrors = mErrors;
    }
}
