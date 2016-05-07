package com.arwandar.myseriesaddict.data.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by olivi on 07/05/2016.
 */
public class ErrorsComplex implements Serializable {

    public List<Errors> mErrors;

    public List<Errors> getmErrors() {
        return mErrors;
    }

    public void setmErrors(List<Errors> mErrors) {
        this.mErrors = mErrors;
    }
}
