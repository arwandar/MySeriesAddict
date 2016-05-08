package com.arwandar.myseriesaddict.ui.adpater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.arwandar.myseriesaddict.ui.fragment.ArchivedShowsListFragment;
import com.arwandar.myseriesaddict.ui.fragment.FriendsFragment;
import com.arwandar.myseriesaddict.ui.fragment.PendingShowsListFragment;
import com.arwandar.myseriesaddict.ui.fragment.ShowsDetailFragment;

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
                return new PendingShowsListFragment();
            case 2:
                return new ArchivedShowsListFragment();
            case 3:
                return new ShowsDetailFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
