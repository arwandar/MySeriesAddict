package com.arwandar.myseriesaddict.data.service;

import com.arwandar.myseriesaddict.data.AccessToken;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

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
            @Field("code") String code,
            @Field("grant_type") String grantType,
            @Field("client_secret") String client_secret,
            @Field("redirect_uri") String redirect_uri,
            @Field("key") String key,
            @Field("v") String v
    );
}
