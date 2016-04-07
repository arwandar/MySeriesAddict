package com.arwandar.myseriesaddict.data.service;

import com.arwandar.myseriesaddict.data.AccessToken;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by olivi on 01/04/2016.
 */
public interface ILoginService {

    //@Headers({
    //        "X-BetaSeries-Key: a93691358c05",
    //        "X-BetaSeries-Version: 2.4"
    //})

    @FormUrlEncoded
    @POST("/members/access_token")
    Call<AccessToken> getAccessToken(
            @Query("code") String code,
            @Query("client_secret") String client_secret,
            @Query("redirect_uri") String redirect_uri,
            @Query("key") String key,
            @Query("v") String v,
            @Field("client_id") String client_id
    );
}
