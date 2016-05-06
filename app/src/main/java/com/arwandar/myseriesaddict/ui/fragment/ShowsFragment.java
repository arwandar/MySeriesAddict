package com.arwandar.myseriesaddict.ui.fragment;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.common.adpater.ShowsAdapter;
import com.arwandar.myseriesaddict.common.util.ItemClickSupport;
import com.arwandar.myseriesaddict.data.model.Shows;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;

/**
 * Created by Arwandar on 05/05/2016.
 */
public abstract class ShowsFragment extends Fragment {
    protected BroadcastReceiver receiver;
    protected ProgressDialog progress;
    protected ShowsAdapter mAdapter;
    protected List<Shows> mShows = new ArrayList<>();
    protected String ACTION_FOR_INTENT_CALLBACK = "INTENT_CALLBACK";

    @Bind(R.id.shows_list)
    protected RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ShowsListCallback mCallback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getContent();

        View view = inflater.inflate(R.layout.fragment_shows, container, false);
        ButterKnife.bind(this, view);

        mRecyclerView.setHasFixedSize(false);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ShowsAdapter(getActivity(), mShows);
        mRecyclerView.setAdapter(mAdapter);

        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport
                .OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                mCallback.onItemSelected(mShows.get(position));
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ShowsListCallback) {
            mCallback = (ShowsListCallback) context;
        } else {
            throw new RuntimeException(context.toString());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(receiver, new IntentFilter(ACTION_FOR_INTENT_CALLBACK));
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(receiver);
    }

    protected void getContent() {
        progress = ProgressDialog.show(getActivity(), "", "Récupération des séries sur BetaSérie", true);
    }

    @OnItemSelected(R.id.shows_list)
    public void startShowsDetail() {

    }

    public interface ShowsListCallback {
        void onItemSelected(Shows pShows);
    }
}