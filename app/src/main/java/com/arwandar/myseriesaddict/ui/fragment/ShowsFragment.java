package com.arwandar.myseriesaddict.ui.fragment;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.api.model.Shows;
import com.arwandar.myseriesaddict.ui.adpater.ShowsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shows, container, false);
        ButterKnife.bind(this, view);
//
//        mRecyclerView.setHasFixedSize(false);
//
//        mLayoutManager = new LinearLayoutManager(getContext());
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        mAdapter = new ShowsAdapter(getActivity(), mShows);
//        mRecyclerView.setAdapter(mAdapter);
//
//        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport
//                .OnItemClickListener() {
//            @Override
//            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
//                mCallback.onItemSelected(mShows.get(position));
//            }
//        });

        return view;
    }

}