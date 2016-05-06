package com.arwandar.myseriesaddict.data.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by olivi on 06/05/2016.
 */
public class MemberComplexDTO {

    @SerializedName(value = "member")
    public UserDTO user;

    @SerializedName("errors")
    public List<String> errors;

    public UserDTO getUser() {
        return user;
    }

    public List<String> getErrors() {
        return errors;
    }
}
