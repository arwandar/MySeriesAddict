package com.arwandar.myseriesaddict.ui.activity;

import android.os.Bundle;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.api.converter.ShowsComplexConverter;
import com.arwandar.myseriesaddict.api.dto.ShowsComplexDTO;
import com.arwandar.myseriesaddict.api.model.CustomModelShowEpisode;
import com.arwandar.myseriesaddict.api.model.Shows;
import com.arwandar.myseriesaddict.api.model.ShowsComplex;
import com.arwandar.myseriesaddict.api.service.CallManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuickWatchedActivity extends CustomShakableActivity {

    boolean getFavoritesCompleted, getEpisodesCompleted;
    private List<CustomModelShowEpisode> mCustomModelShowEpisodes =
            new ArrayList<CustomModelShowEpisode>();
    private List<Shows> mShowsList = new ArrayList<>();
    private ShowsComplexConverter mShowsComplexConverter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_watched);
        initActivity();

        mShowsComplexConverter = new ShowsComplexConverter();
    }

    /**
     * appel au webservice pour recuperer les données
     */
    protected void getContent() {
        getEpisodesCompleted = false;
        getFavoritesCompleted = false;
        getEpisodes();
        getFavorites();
    }

    /**
     * recuperation des séries favorites
     */
    private void getFavorites() {
        CallManager.getFavoritesShowsAsync(new Callback<ShowsComplexDTO>() {
            @Override
            public void onResponse(Call<ShowsComplexDTO> call,
                    Response<ShowsComplexDTO> response) {
                if (response.isSuccessful()) {
                    ShowsComplex showsComplex =
                            mShowsComplexConverter.convertDtoToShowsComplex(response.body());
                    setmCustomModelShowEpisodes(showsComplex);
                    getFavoritesCompleted = true;
                    makeMatch();
                } else {
                    showErrorLogin(response.code());
                }
            }

            @Override
            public void onFailure(Call<ShowsComplexDTO> call, Throwable t) {
                showError();
            }
        });
    }

    /**
     * tri des resultats pour n'avoir que les séries non archivées ayant des episodes
     *
     * @param pShowsComplex
     */
    private void setmCustomModelShowEpisodes(ShowsComplex pShowsComplex) {
        for (Shows s : pShowsComplex.getmShows()) {
            if (s.getmUser().getmArchived() == "false" &&
                    s.getmUser().getmRemaining() != "0") {
                CustomModelShowEpisode c = new CustomModelShowEpisode();
                c.setmShow(s);
                mCustomModelShowEpisodes.add(c);
            }
        }
    }

    /**
     * recuperation de tous les premiers episodes non vus
     */
    private void getEpisodes() {
        CallManager.getEpisodesListAsync(1, new Callback<ShowsComplexDTO>() {
            @Override
            public void onResponse(Call<ShowsComplexDTO> call,
                    Response<ShowsComplexDTO> response) {
                if (response.isSuccessful()) {
                    mShowsList = mShowsComplexConverter
                            .convertDtoToShowsComplex(response.body()).getmShows();
                    getEpisodesCompleted = true;
                    makeMatch();
                } else {
                    showErrorLogin(response.code());
                }
            }

            @Override
            public void onFailure(Call<ShowsComplexDTO> call, Throwable t) {
                showError();
            }
        });
    }

    /**
     * recherche des lignes communes
     */
    private void makeMatch() {
        if (getEpisodesCompleted && getFavoritesCompleted) {
            for (Shows show : mShowsList) {
                for (CustomModelShowEpisode episode : mCustomModelShowEpisodes) {
                    if (episode.getmShow().getmId().equals(show.getmId())) {
                        episode.setmUnseen(show.getmUnseen().get(0));
                        break;
                    }
                }
            }
        }
    }
}


