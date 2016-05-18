package com.arwandar.myseriesaddict.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.api.converter.EpisodesComplexConverter;
import com.arwandar.myseriesaddict.api.dto.EpisodeComplexDTO;
import com.arwandar.myseriesaddict.api.dto.EpisodesComplexDTO;
import com.arwandar.myseriesaddict.api.model.Episode;
import com.arwandar.myseriesaddict.api.service.CallManager;
import com.arwandar.myseriesaddict.ui.activity.CustomActivity;
import com.arwandar.myseriesaddict.ui.activity.ShowsDetailActivity;
import com.arwandar.myseriesaddict.ui.adpater.ListAdapter.ShowDetailEpisodesExpendableListAdpater;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Arwandar on 11/05/2016.
 */
public class ShowDetailEpisodesFragment extends Fragment {

    @Bind(R.id.shows_detail_episodes)
    ExpandableListView mExpandableListView;
    ShowDetailEpisodesExpendableListAdpater mAdapter;
    List<String> mSeasonsList = new ArrayList<>();
    HashMap<String, List<Episode>> mEpisodeListHashMap = new HashMap<>();

    String showsId;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_show_detail_episodes, container, false);
        ButterKnife.bind(this, view);

        showsId = ((ShowsDetailActivity) getActivity()).getShowId();

        getContent();

        mAdapter = new ShowDetailEpisodesExpendableListAdpater(getContext(), mSeasonsList,
                mEpisodeListHashMap);
        mExpandableListView.setAdapter(mAdapter);

        mExpandableListView.setOnChildClickListener(
                new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v,
                            final int groupPosition, final int childPosition, long id) {
                        markAsWatch(groupPosition, childPosition);

                        return false;
                    }
                });

        return view;
    }

    private void markAsWatch(final int groupPosition, final int childPosition) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (mEpisodeListHashMap.get(mSeasonsList.get(groupPosition)).get(
                childPosition).getmUser().getmSeen().equals("false")) {
            builder.setMessage(
                    "L'épisode " + mEpisodeListHashMap.get(mSeasonsList.get(groupPosition)).get(
                            childPosition).getmCode() + " - " +
                            mEpisodeListHashMap.get(mSeasonsList.get(groupPosition)).get(
                                    childPosition).getmTitle() + " va être marqué comme vu.");
            builder.setPositiveButton(getString(R.string.submit_button),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            makeCallForMarkAsWatched(groupPosition, childPosition);
                        }
                    });
        } else {
            builder.setMessage(
                    "L'épisode " + mEpisodeListHashMap.get(mSeasonsList.get(groupPosition)).get(
                            childPosition).getmCode() + " - " +
                            mEpisodeListHashMap.get(mSeasonsList.get(groupPosition)).get(
                                    childPosition).getmTitle() + " va être marqué comme non vu.");
            builder.setPositiveButton(getString(R.string.submit_button),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            makeCallForMarkAsUnwatched(groupPosition, childPosition);
                        }
                    });
        }
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
                mEpisodeListHashMap.get(mSeasonsList.get(groupPosition)).get(childPosition)
                        .getmId();
        CallManager.markEpisodeAsWatchedAsync(episodeId, new Callback<EpisodeComplexDTO>() {
            @Override
            public void onResponse(Call<EpisodeComplexDTO> call,
                    Response<EpisodeComplexDTO> response) {
                if (response.isSuccessful()) {
                    String toastString = mEpisodeListHashMap.get(mSeasonsList.get(groupPosition))
                            .get(childPosition).getmCode() + " a été marqué comme vu.";
                    Toast.makeText(getContext(), toastString, Toast.LENGTH_SHORT).show();
                    mEpisodeListHashMap.get(mSeasonsList.get(groupPosition)).get(
                            childPosition).getmUser().setmSeen("true");
                    mAdapter.notifyDataSetChanged();
                } else {
                    ((CustomActivity) getActivity()).showErrorLogin(response.code());
                }
            }

            @Override
            public void onFailure(Call<EpisodeComplexDTO> call, Throwable t) {
                ((CustomActivity) getActivity()).showError();
            }
        });
    }

    private void makeCallForMarkAsUnwatched(final int groupPosition, final int childPosition) {
        String episodeId =
                mEpisodeListHashMap.get(mSeasonsList.get(groupPosition)).get(childPosition)
                        .getmId();
        CallManager.markEpisodeAsUnWatchedAsync(episodeId, new Callback<EpisodeComplexDTO>() {
            @Override
            public void onResponse(Call<EpisodeComplexDTO> call,
                    Response<EpisodeComplexDTO> response) {
                if (response.isSuccessful()) {
                    String toastString = mEpisodeListHashMap.get(mSeasonsList.get(groupPosition))
                            .get(childPosition).getmCode() + " a été marqué comme non vu.";
                    Toast.makeText(getContext(), toastString, Toast.LENGTH_SHORT).show();
                    mEpisodeListHashMap.get(mSeasonsList.get(groupPosition)).get(
                            childPosition).getmUser().setmSeen("false");
                    mAdapter.notifyDataSetChanged();
                } else {
                    ((CustomActivity) getActivity()).showErrorLogin(response.code());
                }
            }

            @Override
            public void onFailure(Call<EpisodeComplexDTO> call, Throwable t) {
                ((CustomActivity) getActivity()).showError();
            }
        });
    }

    private void getContent() {
        ((ShowsDetailActivity) getActivity()).setRefreshing();
        CallManager.getEpisodesFromShow(showsId, new Callback<EpisodesComplexDTO>() {
            @Override
            public void onResponse(Call<EpisodesComplexDTO> call,
                    Response<EpisodesComplexDTO> response) {
                if (response.isSuccessful()) {
                    EpisodesComplexConverter converter = new EpisodesComplexConverter();
                    List<Episode> episodeList = new ArrayList<>();
                    for (Episode ep : converter.convertDtoToEpisodesComplex(response.body())
                            .getmEpisodes()) {
                        episodeList.add(ep);
                    }
                    setData(episodeList);
                    ((ShowsDetailActivity) getActivity()).isEpisodesLoaded(true);
                    ((ShowsDetailActivity) getActivity()).onDataLoaded();
                } else {
                    ((CustomActivity) getActivity()).showErrorLogin(response.code());
                }
            }

            @Override
            public void onFailure(Call<EpisodesComplexDTO> call, Throwable t) {
                ((CustomActivity) getActivity()).showError();
            }
        });
    }

    private void setData(List<Episode> pEpisodes) {

        for (Episode ep : pEpisodes) {
            if (mEpisodeListHashMap.containsKey(ep.getmSeason())) {
                mEpisodeListHashMap.get(ep.getmSeason()).add(ep);
            } else {
                mSeasonsList.add(ep.getmSeason());
                List<Episode> list = new ArrayList<>();
                list.add(ep);
                mEpisodeListHashMap.put(ep.getmSeason(), list);
            }
        }
        mAdapter.notifyDataSetChanged();
    }
}
