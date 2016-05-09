package com.arwandar.myseriesaddict.ui.fragment;

import com.arwandar.myseriesaddict.R;

/**
 * Created by Arwandar on 05/05/2016.
 */
public class PendingShowsListFragment extends ShowsListFragment {
    public PendingShowsListFragment() {
        this.wantPending = "true";
        this.title = R.string.pending_shows_label;
    }

}
