package com.arwandar.myseriesaddict.data.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by olivi on 31/03/2016.
 */
public class UserDTO {
    @SerializedName("login")
    private String mLogin;

    public String getLogin() {
        return mLogin;
    }
}
