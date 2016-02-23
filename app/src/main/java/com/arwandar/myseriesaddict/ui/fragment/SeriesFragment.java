package com.arwandar.myseriesaddict.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.common.adpater.SeriesAdapter;
import com.arwandar.myseriesaddict.common.util.ItemClickSupport;
import com.arwandar.myseriesaddict.factory.SeriesFactory;
import com.arwandar.myseriesaddict.model.Series;

import java.util.ArrayList;
import java.util.List;

public class SeriesFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private SeriesAdapter mAdapter;
    private List<Series> mSeries = new ArrayList<>();
    private SeriesListCallback mCallback;

    public SeriesFragment() {
        mSeries = SeriesFactory.getSeriesList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_series, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.series_list);
        mRecyclerView.setHasFixedSize(false);
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

    public void ChangeList(List<Series> list) {
        mSeries.clear();
        mSeries = list;
    }

    public interface SeriesListCallback {
        void onItemSelected(Series series);
    }
}
