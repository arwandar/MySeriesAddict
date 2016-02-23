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
import com.arwandar.myseriesaddict.common.adpater.SeriesAdapter;
import com.arwandar.myseriesaddict.common.task.FetchSeriesTask;
import com.arwandar.myseriesaddict.common.util.ItemClickSupport;
import com.arwandar.myseriesaddict.model.Series;

import java.util.ArrayList;
import java.util.List;

public class SeriesFragment extends Fragment {
    protected BroadcastReceiver receiver;
    protected ProgressDialog progress;
    protected SeriesAdapter mAdapter;
    protected List<Series> mSeries = new ArrayList<>();
    protected String mContent;
    protected String ACTION_FOR_INTENT_CALLBACK = "INTENT_CALLBACK";
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private SeriesListCallback mCallback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_series, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.series_list);
        mRecyclerView.setHasFixedSize(false);
        getContent();

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new SeriesAdapter(getActivity(), mSeries);
        mRecyclerView.setAdapter(mAdapter);

        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport
                .OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                mCallback.onItemSelected(mSeries.get(position));
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
        if (context instanceof SeriesListCallback) {
            mCallback = (SeriesListCallback) context;
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
        mSeries.clear();
        try {
            FetchSeriesTask task = new FetchSeriesTask(getActivity(), ACTION_FOR_INTENT_CALLBACK);
            task.execute();
            progress = ProgressDialog.show(getActivity(), "", "Récupération des séries sur BetaSérie", true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface SeriesListCallback {
        void onItemSelected(Series series);
    }
}
