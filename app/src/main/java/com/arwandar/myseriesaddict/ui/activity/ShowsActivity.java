package com.arwandar.myseriesaddict.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.common.adpater.ShowsPagerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ShowsActivity extends AppCompatActivity {

    @Bind(R.id.shows_tabs)
    TabLayout tabLayout;
    @Bind(R.id.shows_toolbar)
    Toolbar toolbar;
    @Bind(R.id.shows_shows_pager)
    ViewPager mViewPager;
    private ShowsPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shows);

        ButterKnife.bind(this);

        tabLayout.addTab(tabLayout.newTab().setText(R.string.pending_shows_label));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.archived_shows_label));
        tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);

        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAdapter = new ShowsPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        mViewPager.setOffscreenPageLimit(mAdapter.getCount());
        mViewPager.setAdapter(mAdapter);
    }
}
