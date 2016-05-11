package com.arwandar.myseriesaddict.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ImageView;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.api.converter.EpisodesComplexConverter;
import com.arwandar.myseriesaddict.api.converter.ShowDisplayComplexConverter;
import com.arwandar.myseriesaddict.api.dto.EpisodesComplexDTO;
import com.arwandar.myseriesaddict.api.dto.ShowDisplayComplexDTO;
import com.arwandar.myseriesaddict.api.model.Episode;
import com.arwandar.myseriesaddict.api.model.Shows;
import com.arwandar.myseriesaddict.api.service.CallManager;
import com.arwandar.myseriesaddict.ui.adpater.PagerAdapter.ShowDetailPagerAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowsDetailActivity extends CustomSwipeAndShakableActivity {

    private final List<Episode> mEpisodeList = new ArrayList<>();
    @Bind(R.id.swipeRefreshLayout)
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    Shows mShows;
    @Bind((R.id.tabs))
    TabLayout mTabLayout;
    @Bind(R.id.shows_detail_view_pager)
    ViewPager mViewPager;
    ShowDetailPagerAdapter mAdapter;
    String showsId;
    boolean isEpisodesLoaded, isDetailsLoaded;

    public Shows getShows() {
        return mShows;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shows_detail);

        initActivity();

        initTabLayout();

        showsId = getIntent().getStringExtra("showsId");

        getContent();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getContent();
            }
        });

        mAdapter = new ShowDetailPagerAdapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(mAdapter.getCount());
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);


    }

    private void initTabLayout() {
        mTabLayout.addTab(mTabLayout.newTab().setText("Détails"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Episodes"));
        mTabLayout.setTabGravity(mTabLayout.GRAVITY_FILL);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /**
     * appel au webservice pour recuperer les données
     */
    protected void getContent() {
        mSwipeRefreshLayout.setRefreshing(true);
        isEpisodesLoaded = false;
        isDetailsLoaded = false;
        getDetails();
        getEpisodes();
    }

    private void getEpisodes() {
        CallManager.getEpisodesFromShow(showsId, new Callback<EpisodesComplexDTO>() {
            @Override
            public void onResponse(Call<EpisodesComplexDTO> call,
                    Response<EpisodesComplexDTO> response) {
                if (response.isSuccessful()) {
                    EpisodesComplexConverter converter = new EpisodesComplexConverter();
                    mEpisodeList.clear();
                    for (Episode ep : converter.convertDtoToEpisodesComplex(response.body())
                            .getmEpisodes()) {
                        mEpisodeList.add(ep);
                    }
                } else {
                    showErrorLogin(response.code());
                }
                isEpisodesLoaded = true;
                onDataLoaded();
            }

            @Override
            public void onFailure(Call<EpisodesComplexDTO> call, Throwable t) {
                showError();
            }
        });
    }

    private void getDetails() {
        CallManager.getShowDisplayAsync(showsId, new Callback<ShowDisplayComplexDTO>() {
            @Override
            public void onResponse(Call<ShowDisplayComplexDTO> call,
                    Response<ShowDisplayComplexDTO> response) {
                if (response.isSuccessful()) {
                    ShowDisplayComplexConverter converter = new ShowDisplayComplexConverter();
                    mShows = converter.convertDtoToShowDisplayComplex(response.body()).getmShow();
                } else {
                    showErrorLogin(response.code());
                }
                isDetailsLoaded = true;
                onDataLoaded();
            }

            @Override
            public void onFailure(Call<ShowDisplayComplexDTO> call, Throwable t) {
                mSwipeRefreshLayout.setRefreshing(false);
                showError();
            }
        });
    }

    private void onDataLoaded() {
        if (isEpisodesLoaded && isDetailsLoaded) {
            mSwipeRefreshLayout.setRefreshing(false);
            setUI();
            //TODO reconstruire les fragments
            //mViewPager.destroyDrawingCache();
            mAdapter.notifyDataSetChanged();
            // mViewPager.setCurrentItem(0);
        }
    }

    /**
     * mise à jour des champs avec les infos recupérées
     */
    private void setUI() {
        ImageView image = (ImageView) findViewById(R.id.shows_detail_image);
        Picasso.with(ShowsDetailActivity.this).load(mShows.getmImages().getmShow()).into(image);
    }
}
