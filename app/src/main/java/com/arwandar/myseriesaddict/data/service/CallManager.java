package com.arwandar.myseriesaddict.data.service;

import com.arwandar.myseriesaddict.data.AccessToken;
import com.arwandar.myseriesaddict.data.ServiceGenerator;
import com.arwandar.myseriesaddict.data.converter.UsersConverter;
import com.arwandar.myseriesaddict.data.dto.UsersDTO;
import com.arwandar.myseriesaddict.model.Users;

import java.io.IOException;

import retrofit2.Call;

/**
 * Created by olivi on 17/04/2016.
 */
public class CallManager {
    private static final String clientId = "a93691358c05";
    private static final String clientSecret = "17d90f0c382e7623a09c6c29d3519028";
    private static final String version = "2.4";
    private static final String redirectUri = "http://127.0.0.1";

    public static void getAccessToken(String code) {
        IBetaSeriesService service = ServiceGenerator.createService(IBetaSeriesService.class);
        Call<AccessToken> call = service.getAccessToken(code, clientSecret, redirectUri, clientId, version, clientId);
        try {
            AccessToken accessToken = call.execute().body();
            ServiceGenerator.setApiToken(accessToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Users getFriendsList() {
        IBetaSeriesService service = ServiceGenerator.createService(IBetaSeriesService.class);
        Call<UsersDTO> call = service.getFriendsList();
        try {
            UsersDTO list = call.execute().body();
            UsersConverter converter = new UsersConverter();
            return converter.convertDtoToUsers(list);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
