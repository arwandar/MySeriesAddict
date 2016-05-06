package com.arwandar.myseriesaddict.data.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by olivi on 06/05/2016.
 */
public class MemberComplex implements Serializable {

    public User user;
    public List<String> errors;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
