package com.arwandar.myseriesaddict.ui.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.api.converter.MemberComplexConverter;
import com.arwandar.myseriesaddict.api.dto.MemberComplexDTO;
import com.arwandar.myseriesaddict.api.model.Shows;
import com.arwandar.myseriesaddict.api.service.CallManager;
import com.arwandar.myseriesaddict.ui.ItemClickSupport;
import com.arwandar.myseriesaddict.ui.activity.CustomActivity;
import com.arwandar.myseriesaddict.ui.activity.ShowsDetailActivity;
import com.arwandar.myseriesaddict.ui.adpater.ShowsAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arwandar on 05/05/2016.
 */
public abstract class ShowsListFragment extends Fragment {
    protected List<Shows> mShows = new ArrayList<>();
    @Bind(R.id.shows_list)
    protected RecyclerView mRecyclerView;
    @Bind(R.id.swipeRefreshLayout)
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    protected ShowsAdapter mAdapter;

    protected String wantPending;

    protected ProgressDialog progress;

    protected int title;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shows_list, container, false);
        ButterKnife.bind(this, view);

        getActivity().setTitle(title);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ShowsAdapter(getActivity(), mShows);
        mRecyclerView.setAdapter(mAdapter);

        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(getActivity(), ShowsDetailActivity.class);
                intent.putExtra("showsId", mShows.get(position).getmId());
                startActivity(intent);

            }
        });
        ItemClickSupport.addTo(mRecyclerView).setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(getActivity(), ShowsDetailActivity.class);
                intent.putExtra("showsId", mShows.get(position).getmId());
                startActivity(intent);
                return false;
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                getContent();
            }
        });

        getContent();
        return view;
    }

    void getContent() {
        CallManager.getMemberInfosAsync(new Callback<MemberComplexDTO>() {
            @Override
            public void onResponse(Call<MemberComplexDTO> call, Response<MemberComplexDTO> response) {
                // progress.dismiss();
                if (response.isSuccessful()) {
                    MemberComplexConverter converter = new MemberComplexConverter();
                    mShows.clear();
                    for (Shows shows : converter.convertDtoToMember(response.body()).getUser().getmShows()) {
                        if (shows.getmUser().getmArchived().equals(wantPending)) mShows.add(shows);
                    }
                    Collections.sort(mShows);
                    mAdapter.notifyDataSetChanged();
                    if (mSwipeRefreshLayout.isRefreshing()) {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }


                } else {
                    ((CustomActivity) getActivity()).showErrorLogin(response.code());
                    if (mSwipeRefreshLayout.isRefreshing()) {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }
            }

            @Override
            public void onFailure(Call<MemberComplexDTO> call, Throwable t) {
                ((CustomActivity) getActivity()).showError();
                if (mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }
}