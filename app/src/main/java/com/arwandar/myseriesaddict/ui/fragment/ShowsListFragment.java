package com.arwandar.myseriesaddict.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.api.AppContext;
import com.arwandar.myseriesaddict.api.model.Shows;
import com.arwandar.myseriesaddict.ui.ItemClickSupport;
import com.arwandar.myseriesaddict.ui.activity.BaseActivity;
import com.arwandar.myseriesaddict.ui.adpater.ShowsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Arwandar on 05/05/2016.
 */
public abstract class ShowsListFragment extends Fragment {
    protected List<Shows> mShows = new ArrayList<>();
    @Bind(R.id.shows_list)
    protected RecyclerView mRecyclerView;
    protected ShowsAdapter mAdapter;

    protected ProgressDialog progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shows_list, container, false);
        ButterKnife.bind(this, view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ShowsAdapter(getActivity(), mShows);
        mRecyclerView.setAdapter(mAdapter);

        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                String toDiplay = mShows.get(position).getmTitle();
                Snackbar snack = Snackbar.make(recyclerView, toDiplay, Snackbar.LENGTH_LONG);
                snack.show();
            }
        });
        ItemClickSupport.addTo(mRecyclerView).setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
                AppContext.setShowsSelected(mShows.get(position));
                ((BaseActivity) getActivity()).setFragment(3);
                return false;
            }
        });

        progress = ProgressDialog.show(getActivity(), "Patientez",
                "Chargement de la liste", true);
        getContent();


        return view;
    }

    abstract void getContent();
}