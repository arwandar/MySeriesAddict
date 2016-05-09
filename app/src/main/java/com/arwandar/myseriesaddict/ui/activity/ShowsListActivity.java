package com.arwandar.myseriesaddict.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.ui.adpater.ShowsListPagerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ShowsListActivity extends CustomActivity {
    @Bind(R.id.content_shows_list)
    ViewPager mViewPager;

    private ShowsListPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shows_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setCustomNavBar();

        mAdapter = new ShowsListPagerAdapter(getSupportFragmentManager(), 0);
        mViewPager.setOffscreenPageLimit(mAdapter.getCount());
        mViewPager.setAdapter(mAdapter);
    }
}
