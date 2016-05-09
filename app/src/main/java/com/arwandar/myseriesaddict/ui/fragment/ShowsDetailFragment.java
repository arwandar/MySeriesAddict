package com.arwandar.myseriesaddict.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.api.AppContext;
import com.arwandar.myseriesaddict.api.model.Shows;

import butterknife.ButterKnife;

/**
 * Created by Arwandar on 08/05/2016.
 */
public class ShowsDetailFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shows_detail, container, false);
        ButterKnife.bind(this, view);

        Shows shows = AppContext.getShowsSelected();


        return view;
    }
}
