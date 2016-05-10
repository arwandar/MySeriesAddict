package com.arwandar.myseriesaddict.api.service;

import com.arwandar.myseriesaddict.api.SharedPrefsSingleton;
import com.arwandar.myseriesaddict.api.dto.EpisodeComplexDTO;
import com.arwandar.myseriesaddict.api.dto.ErrorsComplexDTO;
import com.arwandar.myseriesaddict.api.dto.MemberComplexDTO;
import com.arwandar.myseriesaddict.api.dto.ShowDisplayComplexDTO;
import com.arwandar.myseriesaddict.api.dto.ShowsComplexDTO;
import com.arwandar.myseriesaddict.api.dto.UsersDTO;
import com.arwandar.myseriesaddict.api.model.AccessToken;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by olivi on 17/04/2016.
 */
public class CallManager {

    //Les premières méthodes de chaque 'couple' sont synchrones, les deuxièmes asynchrones
    //Pour utiliser l'asyn, appeler un CallManager.{methodeAsync) avec un new CallBack (AS créé tout bien tout seul)
    //Dans le @OnResponse, mettre ces deux lignes
    //TComplexConverter converter  = new TComplexConverter();
    //TObject object = converter.convertDtoToTObject(response.body());

    public static void getAccessToken(String code) {

        IBetaSeriesService service = ServiceGenerator.createService(IBetaSeriesService.class);
        Call<AccessToken> call =
                service.getAccessToken(code, SharedPrefsSingleton.getClientSecret(),
                        SharedPrefsSingleton.getRedirectURI(), SharedPrefsSingleton.getClientId(),
                        SharedPrefsSingleton.getVersion(), SharedPrefsSingleton.getClientId());
        try {
            AccessToken accessToken = call.execute().body();
            SharedPrefsSingleton.setAccessToken(accessToken.getAccessToken());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getFriendsListAsync(final Callback<UsersDTO> callback) {
        IBetaSeriesService service = ServiceGenerator.createService(IBetaSeriesService.class);
        Call<UsersDTO> call = service.getFriendsList();
        call.enqueue(callback);
    }

    public static void getFavoritesShowsAsync(final Callback<ShowsComplexDTO> callback) {
        IBetaSeriesService service = ServiceGenerator.createService(IBetaSeriesService.class);
        Call<ShowsComplexDTO> call = service.getFavoritesShows();
        call.enqueue(callback);
    }

    public static void getEpisodesListAsync(final Callback<ShowsComplexDTO> callback) {
        getEpisodesListAsync(null, callback);
    }

    public static void getEpisodesListAsync(Integer limit,
            final Callback<ShowsComplexDTO> callback) {
        if (limit <= 0) limit = null;
        IBetaSeriesService service = ServiceGenerator.createService(IBetaSeriesService.class);
        Call<ShowsComplexDTO> call = service.getEpisodesList(limit, "true");
        call.enqueue(callback);
    }

    public static void markEpisodeAsWatchedAsync(String episodeId,
            final Callback<EpisodeComplexDTO> callback) {
        IBetaSeriesService service = ServiceGenerator.createService(IBetaSeriesService.class);
        Call<EpisodeComplexDTO> call = service.markEpisodeAsWatched(episodeId);
        call.enqueue(callback);
    }

    public static void getMemberInfosAsync(final Callback<MemberComplexDTO> callback) {
        getMemberInfosAsync(false, callback);
    }

    public static void getMemberInfosAsync(boolean isSummary,
            final Callback<MemberComplexDTO> callback) {
        IBetaSeriesService service = ServiceGenerator.createService(IBetaSeriesService.class);
        Call<MemberComplexDTO> call = service.getMemberInfos("shows", isSummary ? "true" : "");
        call.enqueue(callback);
    }

    public static void getFriendsInfosAsync(long id, final Callback<MemberComplexDTO> callback) {
        IBetaSeriesService service = ServiceGenerator.createService(IBetaSeriesService.class);
        Call<MemberComplexDTO> call = service.getFriendInfos("true", id);
        call.enqueue(callback);
    }

    public static void getEpisodeDisplayAsync(String episodeId,
            final Callback<EpisodeComplexDTO> callback) {
        IBetaSeriesService service = ServiceGenerator.createService(IBetaSeriesService.class);
        Call<EpisodeComplexDTO> call = service.getEpisodeDisplay(episodeId);
        call.enqueue(callback);
    }

    public static void getShowDisplayAsync(String showId,
            final Callback<ShowDisplayComplexDTO> callback) {
        IBetaSeriesService service = ServiceGenerator.createService(IBetaSeriesService.class);
        Call<ShowDisplayComplexDTO> call = service.getShowDisplay(showId);
        call.enqueue(callback);
    }

    public static void markShowAsArchivedAsync(String showId,
            final Callback<ShowDisplayComplexDTO> callback) {
        IBetaSeriesService service = ServiceGenerator.createService(IBetaSeriesService.class);
        Call<ShowDisplayComplexDTO> call = service.markShowAsArchived(showId);
        call.enqueue(callback);
    }

    public static void destroyTokenAsync(final Callback<ErrorsComplexDTO> callback) {
        IBetaSeriesService service = ServiceGenerator.createService(IBetaSeriesService.class);
        Call<ErrorsComplexDTO> call = service.destroyToken();
        call.enqueue(callback);
    }

    public static void markShowAsFavoriteAsync(String showId,
            final Callback<ShowDisplayComplexDTO> callback) {
        IBetaSeriesService service = ServiceGenerator.createService(IBetaSeriesService.class);
        Call<ShowDisplayComplexDTO> call = service.markShowAsFavorite(showId);
        call.enqueue(callback);
    }

    public static void deleteShowFromArchivedAsync(String showId,
            final Callback<ShowDisplayComplexDTO> callback) {
        IBetaSeriesService service = ServiceGenerator.createService(IBetaSeriesService.class);
        Call<ShowDisplayComplexDTO> call = service.deleteShowFromArchive(showId);
        call.enqueue(callback);
    }

    public static void deleteShowFromFavoriteAsync(String showId,
            final Callback<ShowDisplayComplexDTO> callback) {
        IBetaSeriesService service = ServiceGenerator.createService(IBetaSeriesService.class);
        Call<ShowDisplayComplexDTO> call = service.deleteShowFromFavorite(showId);
        call.enqueue(callback);
    }
}
