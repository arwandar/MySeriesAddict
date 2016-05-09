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
import com.arwandar.myseriesaddict.api.converter.ShowsComplexConverter;
import com.arwandar.myseriesaddict.api.dto.MemberComplexDTO;
import com.arwandar.myseriesaddict.api.dto.ShowsComplexDTO;
import com.arwandar.myseriesaddict.api.model.CustomModelShowEpisode;
import com.arwandar.myseriesaddict.api.model.Shows;
import com.arwandar.myseriesaddict.api.model.ShowsComplex;
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


    public List<CustomModelShowEpisode> list = new ArrayList<CustomModelShowEpisode>();

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


        CallManager.getFavoritesShowsAsync(new Callback<ShowsComplexDTO>() {
            @Override
            public void onResponse(Call<ShowsComplexDTO> call, Response<ShowsComplexDTO> response) {
                final ShowsComplexConverter showsComplexConverter = new ShowsComplexConverter();
                ShowsComplex showsComplex = showsComplexConverter.convertDtoToShowsComplex(response.body());

                for (Shows s : showsComplex.getmShows()) {
                    if (s.getmUser().getmArchived() == "false" && s.getmUser().getmRemaining() != "0") {
                        CustomModelShowEpisode c = new CustomModelShowEpisode();
                        c.setmShow(s);
                        list.add(c);
                    }
                }
                //Ici, on a récupéré toutes les séries favorites non archivées avec un épisode au moins à voir
                //On va matcher avec les épisodes non-vus

                CallManager.getEpisodesListAsync(1, new Callback<ShowsComplexDTO>() {
                    @Override
                    public void onResponse(Call<ShowsComplexDTO> call, Response<ShowsComplexDTO> response) {
                        for (Shows show : showsComplexConverter.convertDtoToShowsComplex(response.body()).getmShows()) {
                            //On vient vérifier que les séries sont présentes dans les deux listes
                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).getmShow().getmId().equals(show.getmId())) {
                                    list.get(i).setmUnseen(show.getmUnseen().get(0));
                                    break;
                                }
                            }
                        }

                        System.out.print("FINI DE RECUPERER TOUS LES EPISODES ! ");

                    }

                    @Override
                    public void onFailure(Call<ShowsComplexDTO> call, Throwable t) {

                    }
                });


            }

            @Override
            public void onFailure(Call<ShowsComplexDTO> call, Throwable t) {

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