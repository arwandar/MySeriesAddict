package com.arwandar.myseriesaddict.ui.adpater.ListAdapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.api.model.User;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Arwandar on 05/05/2016.
 */
public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder> {

    private Activity mActivity;
    private List<User> mUsersList;

    public FriendsAdapter(Activity pActivity, List<User> pUsersList) {
        mActivity = pActivity;
        mUsersList = pUsersList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.small_item_friend, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final User user = mUsersList.get(position);

        if (user.getmAvatar() == null) {
            holder.mPictureView.setBackgroundResource(R.mipmap.ic_launcher);
        } else {
            Picasso.with(mActivity).load(user.getmAvatar()).into(holder.mPictureView);
        }
        holder.mUserName.setText(user.getmLogin());
        holder.mUserXp.setText(String.format("%s xp", user.getmXp()));
    }

    @Override
    public int getItemCount() {
        return mUsersList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind((R.id.friend_name_textview))
        protected TextView mUserName;
        @Bind(R.id.friend_picture)
        protected ImageView mPictureView;
        @Bind((R.id.friend_xp_textview))
        protected TextView mUserXp;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
