package com.arwandar.myseriesaddict.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.api.converter.EpisodesComplexConverter;
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

        return view;
    }

    private void getContent() {
        ((ShowsDetailActivity) getActivity()).setRefreshing();
        CallManager.getEpisodesFromShow(showsId, new Callback<EpisodesComplexDTO>() {
            @Override
            public void onResponse(Call<EpisodesComplexDTO> call,
                    Response<EpisodesComplexDTO> response) {
                if (response.isSuccessful()) {
                    EpisodesComplexConverter converter = new EpisodesComplexConverter();
                    List<Episode> episodeList = new ArrayList<Episode>();
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
