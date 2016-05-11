package com.arwandar.myseriesaddict.ui.adpater.PagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.arwandar.myseriesaddict.ui.fragment.ShowDetailEpisodesFragment;
import com.arwandar.myseriesaddict.ui.fragment.ShowDetailInfoFragment;

/**
 * Created by Arwandar on 11/05/2016.
 */
public class ShowDetailPagerAdapter extends FragmentStatePagerAdapter {

    public ShowDetailPagerAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ShowDetailInfoFragment();
            case 1:
                return new ShowDetailEpisodesFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
