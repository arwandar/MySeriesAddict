package com.arwandar.myseriesaddict.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.api.converter.MemberComplexConverter;
import com.arwandar.myseriesaddict.api.dto.MemberComplexDTO;
import com.arwandar.myseriesaddict.api.model.Shows;
import com.arwandar.myseriesaddict.api.service.CallManager;
import com.arwandar.myseriesaddict.ui.ItemClickSupport;
import com.arwandar.myseriesaddict.ui.adpater.ListAdapter.ShowsAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowsListActivity extends CustomSwipeAndShakableActivity {

    @Bind(R.id.shows_list)
    protected RecyclerView mRecyclerView;
    protected String isArchived;
    protected int title;
    private List<Shows> mShows = new ArrayList<>();
    private ShowsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shows_list);

        initActivity();

        choixListe();

        getContent();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ShowsListActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ShowsAdapter(ShowsListActivity.this, mShows);
        mRecyclerView.setAdapter(mAdapter);

        initItemClikSupport();

    }

    /**
     * set du titre de l'activité et du parametre d'appel de getContent
     * on aurait aussi pu passer par un pattern strategy, mais lourd pour pas grand chose
     */
    private void choixListe() {
        switch (getIntent().getIntExtra("fragmentChoose", 0)) {
            case 0:
                this.isArchived = "true";
                setTitle(R.string.archived_shows_label);
                break;
            default:
                this.isArchived = "false";
                setTitle(R.string.pending_shows_label);
                break;
        }
    }

    private void initItemClikSupport() {
        ItemClickSupport.addTo(mRecyclerView)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Intent intent =
                                new Intent(ShowsListActivity.this, ShowsDetailActivity.class);
                        intent.putExtra("showsId", mShows.get(position).getmId());
                        startActivity(intent);
                    }
                });
        ItemClickSupport.addTo(mRecyclerView)
                .setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClicked(RecyclerView recyclerView, int position,
                            View v) {
                        Intent intent =
                                new Intent(ShowsListActivity.this, ShowsDetailActivity.class);
                        intent.putExtra("showsId", mShows.get(position).getmId());
                        startActivity(intent);
                        return false;
                    }
                });
    }

    void getContent() {
        mSwipeRefreshLayout.setRefreshing(true);
        CallManager.getMemberInfosAsync(new Callback<MemberComplexDTO>() {
            @Override
            public void onResponse(Call<MemberComplexDTO> call,
                    Response<MemberComplexDTO> response) {
                if (response.isSuccessful()) {
                    MemberComplexConverter converter = new MemberComplexConverter();
                    mShows.clear();
                    for (Shows shows : converter.convertDtoToMember(response.body()).getUser()
                            .getmShows()) {
                        if (shows.getmUser().getmArchived().equals(isArchived)) mShows.add(shows);
                    }
                    Collections.sort(mShows);
                    mAdapter.notifyDataSetChanged();
                } else {
                    showErrorLogin(response.code());
                }
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<MemberComplexDTO> call, Throwable t) {
                showError();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
