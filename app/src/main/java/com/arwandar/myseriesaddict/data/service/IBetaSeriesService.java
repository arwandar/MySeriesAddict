package com.arwandar.myseriesaddict.data.service;

import com.arwandar.myseriesaddict.data.dto.UserDTO;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by olivi on 31/03/2016.
 */
public interface IBetaSeriesService {

    @POST("members/access_token")
    Call<UserDTO> getUser();
}
