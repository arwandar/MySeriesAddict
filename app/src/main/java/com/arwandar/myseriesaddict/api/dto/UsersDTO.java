package com.arwandar.myseriesaddict.api.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by olivi on 07/04/2016.
 */
public class UsersDTO {

    @SerializedName(value = "users")
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
