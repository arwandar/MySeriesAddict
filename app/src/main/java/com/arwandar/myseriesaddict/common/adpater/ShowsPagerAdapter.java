package com.arwandar.myseriesaddict.common.adpater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.arwandar.myseriesaddict.ui.fragment.ArchivedShowsFragment;
import com.arwandar.myseriesaddict.ui.fragment.PendingShowsFragment;

/**
 * Created by Arwandar on 05/05/2016.
 */
public class ShowsPagerAdapter extends FragmentStatePagerAdapter {
    private int mShowsTabNumber;

    public ShowsPagerAdapter(android.support.v4.app.FragmentManager fm, int tabNumber) {
        super(fm);
        mShowsTabNumber = tabNumber;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) return new PendingShowsFragment();
        else return new ArchivedShowsFragment();
    }

    @Override
    public int getCount() {
        return mShowsTabNumber;
    }
}
