package com.arwandar.myseriesaddict.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.api.SharedPrefsSingleton;
import com.arwandar.myseriesaddict.api.converter.ShowsComplexConverter;
import com.arwandar.myseriesaddict.api.dto.EpisodeComplexDTO;
import com.arwandar.myseriesaddict.api.dto.ShowsComplexDTO;
import com.arwandar.myseriesaddict.api.model.Shows;
import com.arwandar.myseriesaddict.api.model.ShowsComplex;
import com.arwandar.myseriesaddict.api.model.Unseen;
import com.arwandar.myseriesaddict.api.service.CallManager;
import com.arwandar.myseriesaddict.ui.adpater.ListEpisodesExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodesListActivity extends CustomActivity {

    @Bind(R.id.swipeRefreshLayout)
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.episodes_list_expendable_list_view)
    ExpandableListView mExpandableListView;
    ListEpisodesExpandableListAdapter mAdapter;
    List<Shows> mShowsList;
    HashMap<Shows, List<Unseen>> mShowsListHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episodes_list);
        initActivity();

        mShowsList = new ArrayList<>();
        mShowsListHashMap = new HashMap<>();

        getContent();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getContent();
            }
        });

        mAdapter = new ListEpisodesExpandableListAdapter(this, mShowsList, mShowsListHashMap);
        mExpandableListView.setAdapter(mAdapter);

        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                    final int groupPosition, final int childPosition, long id) {
                markAsWatch(groupPosition, childPosition);

                return false;
            }
        });
    }

    /**
     * creer une alertDialog pour savoir si l'utilisateur veut vraiment marqué l'épisode comme vu
     * et gestion des reponses
     *
     * @param groupPosition
     * @param childPosition
     */
    private void markAsWatch(final int groupPosition, final int childPosition) {
        AlertDialog.Builder builder = new AlertDialog.Builder(EpisodesListActivity.this);
        builder.setMessage(mShowsListHashMap.get(mShowsList.get(groupPosition)).get(
                childPosition).getmCode() + "va être marqué comme vu.");
        builder.setPositiveButton(getString(R.string.submit_button),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        makeCallForMarkAsWatched(groupPosition, childPosition);
                    }
                });
        builder.setNegativeButton(getString(R.string.cancel_button),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void makeCallForMarkAsWatched(final int groupPosition, final int childPosition) {
        String episodeId =
                mShowsListHashMap.get(mShowsList.get(groupPosition)).get(childPosition).getmId();
        CallManager.markEpisodeAsWatchedAsync(episodeId, new Callback<EpisodeComplexDTO>() {
            @Override
            public void onResponse(Call<EpisodeComplexDTO> call,
                    Response<EpisodeComplexDTO> response) {
                if (response.isSuccessful()) {
                    String toastString = mShowsListHashMap.get(mShowsList.get(groupPosition))
                            .get(childPosition).getmCode() + " a été marqué comme vu.";
                    Toast.makeText(getApplicationContext(), toastString, Toast.LENGTH_SHORT).show();
                    removeEpisodeFromList(groupPosition, childPosition);
                } else {
                    showErrorLogin(response.code());
                }
            }

            @Override
            public void onFailure(Call<EpisodeComplexDTO> call, Throwable t) {
                showError();
            }
        });
    }

    /**
     * suppression d'un episode de la liste, et si besoin, du header
     *
     * @param groupPosition
     * @param childPosition
     */
    private void removeEpisodeFromList(int groupPosition, int childPosition) {
        mShowsListHashMap.get(mShowsList.get(groupPosition)).remove(childPosition);
        if (mShowsListHashMap.get(mShowsList.get(groupPosition)).isEmpty()) {
            mShowsListHashMap.remove(mShowsList.get(groupPosition));
            mShowsList.remove(groupPosition);
        }
        mAdapter.notifyDataSetChanged();
    }

    /**
     * appel au webservice pour recuperer les données
     */
    private void getContent() {
        mSwipeRefreshLayout.setRefreshing(true);
        CallManager.getEpisodesListAsync(SharedPrefsSingleton.getEpisodesLimit(),
                new Callback<ShowsComplexDTO>() {
                    @Override
                    public void onResponse(Call<ShowsComplexDTO> call,
                            Response<ShowsComplexDTO> response) {
                        if (response.isSuccessful()) {
                            ShowsComplexConverter showsComplexConverter =
                                    new ShowsComplexConverter();
                            ShowsComplex mShowsComplex =
                                    showsComplexConverter.convertDtoToShowsComplex(response.body());
                            setData(mShowsComplex);
                            mAdapter.notifyDataSetChanged();
                        } else {
                            showErrorLogin(response.code());
                        }
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onFailure(Call<ShowsComplexDTO> call, Throwable t) {
                        showError();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
    }

    /**
     * remplissage des listes utilisées par l'ExpandableListView
     *
     * @param pShowsComplex
     */
    private void setData(ShowsComplex pShowsComplex) {
        mShowsList.clear();
        mShowsListHashMap.clear();
        for (Shows shows : pShowsComplex.getmShows()) {
            mShowsList.add(shows);
            List<Unseen> listEp = new ArrayList<>();
            for (Unseen unseen : shows.getmUnseen()) {
                listEp.add(unseen);
            }
            mShowsListHashMap.put(shows, listEp);
        }
    }
}
