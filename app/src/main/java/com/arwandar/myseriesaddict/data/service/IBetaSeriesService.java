package com.arwandar.myseriesaddict.data.service;

import com.arwandar.myseriesaddict.data.AccessToken;
import com.arwandar.myseriesaddict.data.dto.EpisodeComplexDTO;
import com.arwandar.myseriesaddict.data.dto.ShowsComplexDTO;
import com.arwandar.myseriesaddict.data.dto.UsersDTO;
import com.arwandar.myseriesaddict.data.model.ShowsComplex;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by olivi on 01/04/2016.
 */
public interface IBetaSeriesService {

    @FormUrlEncoded
    @POST("/members/access_token")
    Call<AccessToken> getAccessToken(
            @Field("code") String code,
            @Field("client_secret") String client_secret,
            @Field("redirect_uri") String redirect_uri,
            @Field("key") String key,
            @Field("v") String v,
            @Field("client_id") String client_id
    );

    @GET("/friends/list")
    Call<UsersDTO> getFriendsList();

    @GET("/shows/favorites")
    Call<ShowsComplexDTO> getFavoritesShows();

    @GET("/episodes/list")
    Call<ShowsComplexDTO> getEpisodesList(
            @Query("limit") Integer limit
    );

    @FormUrlEncoded
    @POST("/episodes/watched")
    Call<EpisodeComplexDTO> markEpisodeAsWatched(
      @Field("id") String episodeId
    );
}
