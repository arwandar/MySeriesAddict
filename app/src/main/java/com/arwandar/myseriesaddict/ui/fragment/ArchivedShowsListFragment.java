package com.arwandar.myseriesaddict.ui.fragment;

import com.arwandar.myseriesaddict.R;

/**
 * Created by Arwandar on 05/05/2016.
 */
public class ArchivedShowsListFragment extends ShowsListFragment {
    public ArchivedShowsListFragment() {
        this.wantPending = "true";
        this.title = R.string.archived_shows_label;
    }
}
