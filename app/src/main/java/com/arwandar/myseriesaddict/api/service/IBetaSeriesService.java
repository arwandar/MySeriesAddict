package com.arwandar.myseriesaddict.api.service;

import com.arwandar.myseriesaddict.api.dto.EpisodeComplexDTO;
import com.arwandar.myseriesaddict.api.dto.EpisodesComplexDTO;
import com.arwandar.myseriesaddict.api.dto.ErrorsComplexDTO;
import com.arwandar.myseriesaddict.api.dto.MemberComplexDTO;
import com.arwandar.myseriesaddict.api.dto.ShowDisplayComplexDTO;
import com.arwandar.myseriesaddict.api.dto.ShowsComplexDTO;
import com.arwandar.myseriesaddict.api.dto.UsersDTO;
import com.arwandar.myseriesaddict.api.model.AccessToken;

import retrofit2.Call;
import retrofit2.http.DELETE;
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
            @Query("limit") Integer limit,
            @Query("specials") String includeSpecials
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

    @GET("/members/infos")
    Call<MemberComplexDTO> getFriendInfos(
            @Query("summary") String summary,
            @Query("id") long id
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

    @FormUrlEncoded
    @POST("/shows/favorite")
    Call<ShowDisplayComplexDTO> markShowAsFavorite(
            @Field("id") String showId
    );

    @DELETE("/shows/favorite")
    Call<ShowDisplayComplexDTO> deleteShowFromFavorite(
            @Query("id") String showId
    );

    @DELETE("/shows/archive")
    Call<ShowDisplayComplexDTO> deleteShowFromArchive(
            @Query("id") String showId
    );

    @GET("/shows/episodes")
    Call<EpisodesComplexDTO> getEpisodesFromShow(
            @Query("id") String showId
    );
}
