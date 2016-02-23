package com.arwandar.myseriesaddict.common.adpater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.arwandar.myseriesaddict.ui.fragment.ContinuingSeriesFragment;
import com.arwandar.myseriesaddict.ui.fragment.EndedSeriesFragment;

/**
 * Created by Arwandar on 22/02/2016.
 */
public class SeriesPagerAdapter extends FragmentStatePagerAdapter {
    private int mSeriesTabNumber;

    public SeriesPagerAdapter(android.support.v4.app.FragmentManager fm, int tabNumber) {
        super(fm);
        mSeriesTabNumber = tabNumber;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) return new ContinuingSeriesFragment();
        else return new EndedSeriesFragment();
    }

    @Override
    public int getCount() {
        return mSeriesTabNumber;
    }

}
