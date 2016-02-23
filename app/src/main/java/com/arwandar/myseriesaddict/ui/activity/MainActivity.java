package com.arwandar.myseriesaddict.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.common.adpater.SeriesPagerAdapter;
import com.arwandar.myseriesaddict.model.Series;
import com.arwandar.myseriesaddict.ui.fragment.SeriesFragment;

public class MainActivity extends AppCompatActivity implements SeriesFragment.SeriesListCallback {

    private ViewPager mViewPager;
    private SeriesPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Séries en cours"));
        tabLayout.addTab(tabLayout.newTab().setText("Séries terminées"));
        tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mViewPager = (ViewPager) findViewById(R.id.series_pager);
        mAdapter = new SeriesPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        mViewPager.setOffscreenPageLimit(mAdapter.getCount());
        mViewPager.setAdapter(mAdapter);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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

    public void onItemSelected(Series mSeries) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("seriesSelected", mSeries);
        startActivity(intent);
    }
}
