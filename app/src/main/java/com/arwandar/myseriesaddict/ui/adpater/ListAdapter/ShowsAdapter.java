package com.arwandar.myseriesaddict.ui.adpater.ListAdapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.api.model.Shows;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Arwandar on 05/05/2016.
 */
public class ShowsAdapter extends RecyclerView.Adapter<ShowsAdapter.ViewHolder> {

    private Activity mActivity;
    private List<Shows> mShowsList;

    public ShowsAdapter(Activity activity, List<Shows> pShowsList) {
        mActivity = activity;
        mShowsList = pShowsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.small_item_show, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Shows shows = mShowsList.get(position);

        holder.mNameView.setText(shows.getmTitle());
        holder.mDescriptionView.setText(shows.getmDescription());
        Picasso.with(this.mActivity).load(shows.getmImages().getmBox()).into(holder.mPictureView);
    }

    @Override
    public int getItemCount() {
        return mShowsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.small_item_shows_picture)
        public ImageView mPictureView;
        @Bind(R.id.small_item_shows_name)
        protected TextView mNameView;
        @Bind(R.id.small_item_shows_description)
        protected TextView mDescriptionView;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
