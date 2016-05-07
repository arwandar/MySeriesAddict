package com.arwandar.myseriesaddict.ui.adpater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.arwandar.myseriesaddict.ui.fragment.ArchivedShowsFragment;
import com.arwandar.myseriesaddict.ui.fragment.FriendsFragment;
import com.arwandar.myseriesaddict.ui.fragment.PendingShowsFragment;

/**
 * Created by Arwandar on 05/05/2016.
 */
public class BasePagerAdapter extends FragmentStatePagerAdapter {
    private int mActivityFragment;

    public BasePagerAdapter(android.support.v4.app.FragmentManager fm, int pBaseActivityFragment) {
        super(fm);
        mActivityFragment = pBaseActivityFragment;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FriendsFragment();
            case 1:
                return new PendingShowsFragment();
            default:
                return new ArchivedShowsFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
