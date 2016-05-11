package com.arwandar.myseriesaddict.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arwandar.myseriesaddict.R;

import butterknife.ButterKnife;

/**
 * Created by Arwandar on 11/05/2016.
 */
public class ShowDetailEpisodesFragment extends Fragment {

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_show_detail_info, container, false);
        ButterKnife.bind(this, view);

        return view;
    }
}
