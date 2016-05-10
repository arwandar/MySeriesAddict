package com.arwandar.myseriesaddict.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ExpandableListView;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.api.SharedPrefsSingleton;
import com.arwandar.myseriesaddict.api.converter.ShowsComplexConverter;
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
    ListEpisodesExpandableListAdapter listAdapter;
    @Bind(R.id.episodes_list_expendable_list_view)
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    ShowsComplex mShowsComplex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episodes_list);
        initActivity();

        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();


        getContent();

        mSwipeRefreshLayout.setAnimation(null);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                getContent();
            }
        });

        listAdapter = new ListEpisodesExpandableListAdapter(this, listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);
    }

    private void getContent() {
        CallManager.getEpisodesListAsync(SharedPrefsSingleton.getEpisodesLimit(), new Callback<ShowsComplexDTO>() {
            @Override
            public void onResponse(Call<ShowsComplexDTO> call, Response<ShowsComplexDTO> response) {
                if (response.isSuccessful()) {
                    ShowsComplexConverter showsComplexConverter = new ShowsComplexConverter();
                    mShowsComplex = showsComplexConverter.convertDtoToShowsComplex(response.body());
                    setData();
                    listAdapter.notifyDataSetChanged();
                    mSwipeRefreshLayout.setRefreshing(false);
                } else {
                    showErrorLogin(response.code());
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<ShowsComplexDTO> call, Throwable t) {
                showError();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void setData() {
        listDataHeader.clear();
        listDataChild.clear();

        for (Shows shows : mShowsComplex.getmShows()) {
            listDataHeader.add(shows.getmTitle());
            List<String> listEp = new ArrayList<>();
            for (Unseen unseen : shows.getmUnseen()) {
                listEp.add(unseen.getmCode() + " - " + unseen.getmTitle());
            }
            listDataChild.put(shows.getmTitle(), listEp);
        }
    }

}
