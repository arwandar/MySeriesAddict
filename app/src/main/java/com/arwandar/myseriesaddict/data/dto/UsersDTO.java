package com.arwandar.myseriesaddict.data.dto;

import com.arwandar.myseriesaddict.model.User;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by olivi on 07/04/2016.
 */
public class UsersDTO {

    @SerializedName("users")
    public List<UserDTO> users;

    @SerializedName("errors")
    public List<String> errors;

    public List<UserDTO> getUsers() {
        return users;
    }

    public List<String> getErrors() {
        return errors;
    }
}
