package com.arwandar.myseriesaddict.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.common.adpater.FriendsAdaptater;
import com.arwandar.myseriesaddict.common.util.ItemClickSupport;
import com.arwandar.myseriesaddict.data.model.User;
import com.arwandar.myseriesaddict.data.service.CallManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FriendsActivity extends AppCompatActivity {

    protected List<User> mUsers = new ArrayList<>();

    @Bind(R.id.friends_recycler_view)
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    FriendsAdaptater mAdapter;
    private FriendListCallback mCallback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mUsers = CallManager.getFriendsList().getUsers();

        ButterKnife.bind(this);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new FriendsAdaptater(this, mUsers);
        mRecyclerView.setAdapter(mAdapter);

        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport
                .OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                mCallback.onItemSelected(mUsers.get(position));
            }
        });

    }

    public void onItemSelected(User mUser) {
        Intent intent = new Intent(this, FriendDetailActivity.class);
        intent.putExtra("userSelected", mUser);
        startActivity(intent);
    }

    public interface FriendListCallback {
        void onItemSelected(User user);
    }
}
