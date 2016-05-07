package com.arwandar.myseriesaddict.data.service;

import com.arwandar.myseriesaddict.common.util.SharedPrefsSingleton;
import com.arwandar.myseriesaddict.data.AccessToken;
import com.arwandar.myseriesaddict.data.ServiceGenerator;
import com.arwandar.myseriesaddict.data.converter.EpisodeComplexConverter;
import com.arwandar.myseriesaddict.data.converter.MemberComplexConverter;
import com.arwandar.myseriesaddict.data.converter.ShowDisplayComplexConverter;
import com.arwandar.myseriesaddict.data.converter.ShowsComplexConverter;
import com.arwandar.myseriesaddict.data.converter.UsersConverter;
import com.arwandar.myseriesaddict.data.dto.EpisodeComplexDTO;
import com.arwandar.myseriesaddict.data.dto.MemberComplexDTO;
import com.arwandar.myseriesaddict.data.dto.ShowDisplayComplexDTO;
import com.arwandar.myseriesaddict.data.dto.ShowsComplexDTO;
import com.arwandar.myseriesaddict.data.dto.UsersDTO;
import com.arwandar.myseriesaddict.data.model.EpisodeComplex;
import com.arwandar.myseriesaddict.data.model.MemberComplex;
import com.arwandar.myseriesaddict.data.model.ShowDisplayComplex;
import com.arwandar.myseriesaddict.data.model.ShowsComplex;
import com.arwandar.myseriesaddict.data.model.Users;

import java.io.IOException;

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
        Call<AccessToken> call = service.getAccessToken(code, SharedPrefsSingleton.getClientSecret(),
                SharedPrefsSingleton.getRedirectURI(), SharedPrefsSingleton.getClientId(),
                SharedPrefsSingleton.getVersion(), SharedPrefsSingleton.getClientId());
        try {
            AccessToken accessToken = call.execute().body();
            SharedPrefsSingleton.setAccessToken(accessToken.getAccessToken());
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

    public static void getFriendsListAsync(final Callback<UsersDTO> callback) {
        IBetaSeriesService service = ServiceGenerator.createService(IBetaSeriesService.class);
        Call<UsersDTO> call = service.getFriendsList();
        call.enqueue(callback);
    }

    public static ShowsComplex getFavoritesShows() {
        IBetaSeriesService service = ServiceGenerator.createService(IBetaSeriesService.class);
        Call<ShowsComplexDTO> call = service.getFavoritesShows();
        try {
            ShowsComplexDTO list = call.execute().body();
            ShowsComplexConverter showsComplexConverter = new ShowsComplexConverter();
            return showsComplexConverter.convertDtoToShowsComplex(list);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void getFavoritesShowsAsync(final Callback<ShowsComplexDTO> callback) {
        IBetaSeriesService service = ServiceGenerator.createService(IBetaSeriesService.class);
        Call<ShowsComplexDTO> call = service.getFavoritesShows();
        call.enqueue(callback);
    }

    public static ShowsComplex getEpisodesList() {
        return getEpisodesList(null);
    }

    public static ShowsComplex getEpisodesList(Integer limit) {
        IBetaSeriesService service = ServiceGenerator.createService(IBetaSeriesService.class);
        Call<ShowsComplexDTO> call = service.getEpisodesList(limit);
        try {
            ShowsComplexDTO list = call.execute().body();
            ShowsComplexConverter showsComplexConverter = new ShowsComplexConverter();
            return showsComplexConverter.convertDtoToShowsComplex(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void getEpisodesListAsync(final Callback<ShowsComplexDTO> callback) {
        getEpisodesListAsync(null, callback);
    }

    public static void getEpisodesListAsync(Integer limit, final Callback<ShowsComplexDTO> callback) {
        IBetaSeriesService service = ServiceGenerator.createService(IBetaSeriesService.class);
        Call<ShowsComplexDTO> call = service.getEpisodesList(limit);
        call.enqueue(callback);
    }

    public static EpisodeComplex markEpisodeAsWatched(String episodeId) {
        IBetaSeriesService service = ServiceGenerator.createService(IBetaSeriesService.class);
        Call<EpisodeComplexDTO> call = service.markEpisodeAsWatched(episodeId);
        try {
            EpisodeComplexDTO list = call.execute().body();
            EpisodeComplexConverter episodeComplexConverter = new EpisodeComplexConverter();
            return episodeComplexConverter.convertDtoToEpisodeComplex(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void markEpisodeAsWatchedAsync(String episodeId, final Callback<EpisodeComplexDTO> callback) {
        IBetaSeriesService service = ServiceGenerator.createService(IBetaSeriesService.class);
        Call<EpisodeComplexDTO> call = service.markEpisodeAsWatched(episodeId);
        call.enqueue(callback);
    }


    public static MemberComplex getMemberInfos() {
        IBetaSeriesService service = ServiceGenerator.createService(IBetaSeriesService.class);
        Call<MemberComplexDTO> call = service.getMemberInfos(true);
        try {
            MemberComplexDTO list = call.execute().body();
            MemberComplexConverter converter = new MemberComplexConverter();
            return converter.convertDtoToMember(list);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void getMemberInfosAsync(final Callback<MemberComplexDTO> callback) {
        IBetaSeriesService service = ServiceGenerator.createService(IBetaSeriesService.class);
        Call<MemberComplexDTO> call = service.getMemberInfos(true);
        call.enqueue(callback);
    }

    public static EpisodeComplex getEpisodeDisplay(String episodeId) {
        IBetaSeriesService service = ServiceGenerator.createService(IBetaSeriesService.class);
        Call<EpisodeComplexDTO> call = service.getEpisodeDisplay(episodeId);
        try {
            EpisodeComplexDTO list = call.execute().body();
            EpisodeComplexConverter episodeComplexConverter = new EpisodeComplexConverter();
            return episodeComplexConverter.convertDtoToEpisodeComplex(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void getEpisodeDisplayAsync(String episodeId, final Callback<EpisodeComplexDTO> callback) {
        IBetaSeriesService service = ServiceGenerator.createService(IBetaSeriesService.class);
        Call<EpisodeComplexDTO> call = service.getEpisodeDisplay(episodeId);
        call.enqueue(callback);
    }

    public static ShowDisplayComplex getShowDisplay(String showId) {
        IBetaSeriesService service = ServiceGenerator.createService(IBetaSeriesService.class);
        Call<ShowDisplayComplexDTO> call = service.getShowDisplay(showId);
        try {
            ShowDisplayComplexDTO list = call.execute().body();
            ShowDisplayComplexConverter showDisplayComplexConverter = new ShowDisplayComplexConverter();
            return showDisplayComplexConverter.convertDtoToShowDisplayComplex(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void getShowDisplayAsync(String showId, final Callback<ShowDisplayComplexDTO> callback) {
        IBetaSeriesService service = ServiceGenerator.createService(IBetaSeriesService.class);
        Call<ShowDisplayComplexDTO> call = service.getShowDisplay(showId);
        call.enqueue(callback);
    }

    public static ShowDisplayComplex markShowAsArchived(String showId) {
        IBetaSeriesService service = ServiceGenerator.createService(IBetaSeriesService.class);
        Call<ShowDisplayComplexDTO> call = service.markShowAsArchived(showId);
        try {
            ShowDisplayComplexDTO list = call.execute().body();
            ShowDisplayComplexConverter showDisplayComplexConverter = new ShowDisplayComplexConverter();
            return showDisplayComplexConverter.convertDtoToShowDisplayComplex(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void markShowAsArchivedAsync(String showId, final Callback<ShowDisplayComplexDTO> callback) {
        IBetaSeriesService service = ServiceGenerator.createService(IBetaSeriesService.class);
        Call<ShowDisplayComplexDTO> call = service.markShowAsArchived(showId);
        call.enqueue(callback);
    }
}
