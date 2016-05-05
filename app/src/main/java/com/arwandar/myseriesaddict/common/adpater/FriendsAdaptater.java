package com.arwandar.myseriesaddict.common.adpater;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.data.model.User;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Arwandar on 05/05/2016.
 */
public class FriendsAdaptater extends RecyclerView.Adapter<FriendsAdaptater.ViewHolder> {
    private Activity mActivity;
    private List<User> mUsersList;

    public FriendsAdaptater(Activity activity, List<User> pUsersList) {
        mActivity = activity;
        mUsersList = pUsersList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.friend_small_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = mUsersList.get(position);

        holder.mColorView.setBackgroundColor(user.ismInAccount() ? Color.GREEN : Color.RED);
        holder.mUserName.setText(user.getmLogin());
    }

    @Override
    public int getItemCount() {
        return mUsersList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind((R.id.friend_name_textview))
        protected TextView mUserName;
        @Bind(R.id.friend_color)
        protected View mColorView;

//        private View mColorView;
//        private TextView mUserName;


        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

//            mColorView = v.findViewById(R.id.friend_color);
//            mUserName = (TextView) v.findViewById(R.id.friend_name_textview);
        }
    }
}
