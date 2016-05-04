package com.arwandar.myseriesaddict.data.service;

import android.content.SharedPreferences;

import com.arwandar.myseriesaddict.data.AccessToken;
import com.arwandar.myseriesaddict.data.ServiceGenerator;
import com.arwandar.myseriesaddict.data.converter.ShowsComplexConverter;
import com.arwandar.myseriesaddict.data.converter.UsersConverter;
import com.arwandar.myseriesaddict.data.dto.ShowsComplexDTO;
import com.arwandar.myseriesaddict.data.dto.UsersDTO;
import com.arwandar.myseriesaddict.data.model.ShowsComplex;
import com.arwandar.myseriesaddict.data.model.Users;

import java.io.IOException;

import retrofit2.Call;

/**
 * Created by olivi on 17/04/2016.
 */
public class CallManager {

    public static void getAccessToken(SharedPreferences prefs, String code) {
        IBetaSeriesService service = ServiceGenerator.createService(IBetaSeriesService.class, prefs);
        Call<AccessToken> call = service.getAccessToken(code, prefs.getString("clientSecret", "")
                , prefs.getString("redirectUri", ""), prefs.getString("clientId", "")
                , prefs.getString("version", ""), prefs.getString("clientId", ""));
        try {
            AccessToken accessToken = call.execute().body();
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("accessToken", accessToken.getAccessToken());
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Users getFriendsList(SharedPreferences prefs) {
        IBetaSeriesService service = ServiceGenerator.createService(IBetaSeriesService.class, prefs);
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

    public static ShowsComplex getFavoritesShows(SharedPreferences prefs) {
        IBetaSeriesService service = ServiceGenerator.createService(IBetaSeriesService.class, prefs);
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
}
