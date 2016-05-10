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

public class QuickWatchedActivity extends CustomActivity {

    public List<CustomModelShowEpisode> list = new ArrayList<CustomModelShowEpisode>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_watched);
        initActivity();
    }

    /**
     * appel au webservice pour recuperer les données
     */
    private void getContent() {
        CallManager.getFavoritesShowsAsync(new Callback<ShowsComplexDTO>() {
            @Override
            public void onResponse(Call<ShowsComplexDTO> call,
                    Response<ShowsComplexDTO> response) {
                final ShowsComplexConverter showsComplexConverter =
                        new ShowsComplexConverter();
                ShowsComplex showsComplex =
                        showsComplexConverter.convertDtoToShowsComplex(response.body());

                for (Shows s : showsComplex.getmShows()) {
                    if (s.getmUser().getmArchived() == "false" &&
                            s.getmUser().getmRemaining() != "0") {
                        CustomModelShowEpisode c = new CustomModelShowEpisode();
                        c.setmShow(s);
                        list.add(c);
                    }
                }
                //Ici, on a récupéré toutes les séries favorites non archivées avec un épisode au moins à voir
                //On va matcher avec les épisodes non-vus

                CallManager.getEpisodesListAsync(1, new Callback<ShowsComplexDTO>() {
                    @Override
                    public void onResponse(Call<ShowsComplexDTO> call,
                            Response<ShowsComplexDTO> response) {
                        for (Shows show : showsComplexConverter
                                .convertDtoToShowsComplex(response.body()).getmShows()) {
                            //On vient vérifier que les séries sont présentes dans les deux listes
                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).getmShow().getmId().equals(show.getmId())) {
                                    list.get(i).setmUnseen(show.getmUnseen().get(0));
                                    break;
                                }
                            }
                        }

                        System.out.print("FINI DE RECUPERER TOUS LES EPISODES ! ");
                    }

                    @Override
                    public void onFailure(Call<ShowsComplexDTO> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onFailure(Call<ShowsComplexDTO> call, Throwable t) {
            }
        });
    }
}
