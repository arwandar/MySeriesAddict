package com.arwandar.myseriesaddict.api.service;

import com.arwandar.myseriesaddict.api.model.AccessToken;
import com.arwandar.myseriesaddict.api.dto.EpisodeComplexDTO;
import com.arwandar.myseriesaddict.api.dto.ErrorsComplexDTO;
import com.arwandar.myseriesaddict.api.dto.MemberComplexDTO;
import com.arwandar.myseriesaddict.api.dto.ShowDisplayComplexDTO;
import com.arwandar.myseriesaddict.api.dto.ShowsComplexDTO;
import com.arwandar.myseriesaddict.api.dto.UsersDTO;

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

    @GET("/members/infos")
    Call<MemberComplexDTO> getMemberInfos(
            @Query("only") String only,
            @Query("summary") String summary
    );

    @GET("/episodes/display")
    Call<EpisodeComplexDTO> getEpisodeDisplay(
            @Query("id") String episodeId
    );

    @GET("/shows/display")
    Call<ShowDisplayComplexDTO> getShowDisplay(
            @Query("id") String showId
    );

    @FormUrlEncoded
    @POST("/shows/archive")
    Call<ShowDisplayComplexDTO> markShowAsArchived(
            @Field("id") String showId
    );

    @POST("/members/destroy")
    Call<ErrorsComplexDTO> destroyToken();

}
