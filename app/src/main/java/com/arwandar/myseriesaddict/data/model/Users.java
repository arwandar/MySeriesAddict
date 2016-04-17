package com.arwandar.myseriesaddict.data.model;

import com.arwandar.myseriesaddict.data.model.User;

import java.io.Serializable;
import java.util.List;

/**
 * Created by olivi on 08/04/2016.
 */
public class Users implements Serializable {

    public List<User> users;
    public List<String> errors;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
