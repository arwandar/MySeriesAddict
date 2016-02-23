package com.arwandar.myseriesaddict.ui.fragment;

import com.arwandar.myseriesaddict.factory.SeriesFactory;

/**
 * Created by Arwandar on 22/02/2016.
 */
public class EndedSeriesFragment extends SeriesFragment {
    public EndedSeriesFragment() {
        ChangeList(SeriesFactory.getSeriesEnded());
    }
}
