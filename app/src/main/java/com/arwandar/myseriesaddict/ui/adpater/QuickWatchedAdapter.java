package com.arwandar.myseriesaddict.ui.adpater;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.api.model.CustomModelShowEpisode;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Arwandar on 10/05/2016.
 */
public class QuickWatchedAdapter extends RecyclerView.Adapter<QuickWatchedAdapter.ViewHolder> {

    List<CustomModelShowEpisode> mCustomModelShowEpisodeList;
    Activity mActivity;

    public QuickWatchedAdapter(Activity pActivity, List<CustomModelShowEpisode> pList) {
        mCustomModelShowEpisodeList = pList;
        mActivity = pActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.small_item_quick_watched, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final CustomModelShowEpisode customModelShowEpisode =
                mCustomModelShowEpisodeList.get(position);

        if (customModelShowEpisode.getmShow().getmImages().getmShow() == null) {
            holder.mPictureView.setBackgroundResource(R.mipmap.ic_launcher);
        } else {
            Picasso.with(mActivity).load(customModelShowEpisode.getmShow().getmImages().getmShow())
                    .into(holder.mPictureView);
        }
        holder.mTextView.setText(customModelShowEpisode.getmUnseen().getmCode());
    }

    @Override
    public int getItemCount() {
        return mCustomModelShowEpisodeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind((R.id.small_item_quick_watched_text))
        protected TextView mTextView;
        @Bind(R.id.small_item_quick_watched_picture)
        protected ImageView mPictureView;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
