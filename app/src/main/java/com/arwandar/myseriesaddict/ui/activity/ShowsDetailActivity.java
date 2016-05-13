package com.arwandar.myseriesaddict.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ImageView;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.ui.adpater.PagerAdapter.ShowDetailPagerAdapter;
import com.squareup.picasso.Picasso;

import butterknife.Bind;

public class ShowsDetailActivity extends CustomSwipeAndShakableActivity {

    @Bind((R.id.tabs))
    TabLayout mTabLayout;
    @Bind(R.id.shows_detail_view_pager)
    ViewPager mViewPager;
    @Bind(R.id.shows_detail_image)
    ImageView image;
    ShowDetailPagerAdapter mAdapter;
    String showId, mUrl;
    boolean isEpisodesLoaded = false, isDetailsLoaded = false;

    public String getShowId() {
        return showId;
    }

    public void isEpisodesLoaded(boolean pIsEpisodesLoaded) {
        isEpisodesLoaded = pIsEpisodesLoaded;
    }

    public void setSwipeLayout(SwipeRefreshLayout pSwipeLayout) {
        mSwipeRefreshLayout = pSwipeLayout;
        initSwipe();
    }

    public void isDetailsLoaded(boolean pIsDetailsLoaded) {
        isDetailsLoaded = pIsDetailsLoaded;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shows_detail);

        initActivity();

        initTabLayout();

        showId = getIntent().getExtras().getString("showsId");

        mAdapter = new ShowDetailPagerAdapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(mAdapter.getCount());
        mViewPager.setAdapter(mAdapter);
    }

    private void initTabLayout() {
        mTabLayout.addTab(mTabLayout.newTab().setText("Détails"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Episodes"));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

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
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(true);
        }
        mAdapter.notifyDataSetChanged();
        isEpisodesLoaded = false;
        isDetailsLoaded = false;
    }

    public void onDataLoaded(String url) {
        this.mUrl = url;
        onDataLoaded();
    }

    public void onDataLoaded() {
        if (isEpisodesLoaded && isDetailsLoaded) {
            if (mSwipeRefreshLayout != null) {
                mSwipeRefreshLayout.setRefreshing(false);
            }
            Picasso.with(ShowsDetailActivity.this).load(mUrl).into(image);
            mToolbar.setMinimumHeight(image.getHeight());
        }
    }

    public void setRefreshing() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(true);
        }
    }
}
