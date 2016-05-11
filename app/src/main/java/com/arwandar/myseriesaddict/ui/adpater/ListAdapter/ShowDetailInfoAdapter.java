package com.arwandar.myseriesaddict.ui.adpater.ListAdapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arwandar.myseriesaddict.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by olivi on 11/05/2016.
 */
public class ShowDetailInfoAdapter extends RecyclerView.Adapter<ShowDetailInfoAdapter.ViewHolder> {

    private Activity mActivity;

    public ShowDetailInfoAdapter(Activity activity) {
        mActivity = activity;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.small_item_show, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.shows_detail_title)
        protected TextView mTitleView;
        @Bind(R.id.shows_detail_pending)
        protected TextView mPendingView;
        @Bind(R.id.shows_detail_description)
        protected TextView mDescriptionView;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
