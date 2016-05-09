package com.arwandar.myseriesaddict.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.ui.adpater.ShowsListPagerAdapter;

import butterknife.Bind;

public class ShowsListActivity extends CustomActivity {
    @Bind(R.id.content_shows_list)
    ViewPager mViewPager;

    private ShowsListPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shows_list);

        initActivity();

        mAdapter = new ShowsListPagerAdapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(mAdapter.getCount());
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(getIntent().getIntExtra("fragmentChoose", 0));
    }
}
