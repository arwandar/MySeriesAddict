package com.arwandar.myseriesaddict.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.api.converter.ShowsComplexConverter;
import com.arwandar.myseriesaddict.api.dto.EpisodeComplexDTO;
import com.arwandar.myseriesaddict.api.dto.ShowsComplexDTO;
import com.arwandar.myseriesaddict.api.model.CustomModelShowEpisode;
import com.arwandar.myseriesaddict.api.model.Shows;
import com.arwandar.myseriesaddict.api.model.ShowsComplex;
import com.arwandar.myseriesaddict.api.service.CallManager;
import com.arwandar.myseriesaddict.ui.util.ItemClickSupport;
import com.arwandar.myseriesaddict.ui.adpater.ListAdapter.QuickWatchedAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuickWatchedActivity extends CustomSwipeAndShakableActivity {

    boolean getFavoritesCompleted, getEpisodesCompleted;
    @Bind(R.id.quick_watched_recycler_view)
    RecyclerView mRecyclerView;
    QuickWatchedAdapter mAdapter;
    private List<CustomModelShowEpisode> mCustomModelShowEpisodes =
            new ArrayList<>();
    private List<CustomModelShowEpisode> mCustomModelShowEpisodesCall =
            new ArrayList<>();
    private List<Shows> mShowsList = new ArrayList<>();
    private ShowsComplexConverter mShowsComplexConverter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_watched);
        initActivity();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new QuickWatchedAdapter(this, mCustomModelShowEpisodes);
        mRecyclerView.setAdapter(mAdapter);

        mShowsComplexConverter = new ShowsComplexConverter();

        getContent();

        initItemClikSupport();
    }

    /**
     * appel au webservice pour recuperer les données
     */
    protected void getContent() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(true);
        }
        //mCustomModelShowEpisodes.clear();
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
        mCustomModelShowEpisodesCall.clear();
        for (Shows s : pShowsComplex.getmShows()) {
            if (s.getmUser().getmArchived().equals("false") &&
                    !s.getmUser().getmRemaining().equals("0")) {
                CustomModelShowEpisode c = new CustomModelShowEpisode();
                c.setmShow(s);
                mCustomModelShowEpisodesCall.add(c);
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

                for (CustomModelShowEpisode episode : mCustomModelShowEpisodesCall) {
                    if (!mCustomModelShowEpisodes.contains(episode)) {
                        if (episode.getmShow().getmId().equals(show.getmId())) {
                            episode.setmUnseen(show.getmUnseen().get(0));
                            mCustomModelShowEpisodes.add(episode);
                            // break;
                        }
                    }
                }
            }
            mAdapter.notifyDataSetChanged();
            if (mSwipeRefreshLayout != null) {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }
    }

    private void initItemClikSupport() {
        ItemClickSupport.addTo(mRecyclerView)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, final int position, View
                            v) {
                        String episodeId = mCustomModelShowEpisodes.get(position).getmUnseen()
                                .getmId();
                        CallManager.markEpisodeAsWatchedAsync(episodeId,
                                new Callback<EpisodeComplexDTO>() {
                                    @Override
                                    public void onResponse(Call<EpisodeComplexDTO> call,
                                            Response<EpisodeComplexDTO> response) {
                                        if (response.isSuccessful()) {
                                            String toastString =
                                                    mCustomModelShowEpisodes.get(position)
                                                            .getmUnseen().getmCode() +
                                                            " a été marqué comme vu.";
                                            Toast.makeText(getApplicationContext(), toastString,
                                                    Toast.LENGTH_LONG).show();
                                            mCustomModelShowEpisodes.remove(position);
                                            mAdapter.notifyDataSetChanged();
                                            getContent();
                                        } else {
                                            showErrorLogin(response.code());
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<EpisodeComplexDTO> call,
                                            Throwable t) {
                                        showError();
                                    }
                                });
                    }
                });
        ItemClickSupport.addTo(mRecyclerView)
                .setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClicked(RecyclerView recyclerView, int position,
                            View v) {
                        return false;
                    }
                });
    }
}


