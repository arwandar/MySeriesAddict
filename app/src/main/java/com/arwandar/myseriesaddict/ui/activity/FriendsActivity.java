package com.arwandar.myseriesaddict.ui.activity;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.common.adpater.FriendsAdapter;
import com.arwandar.myseriesaddict.common.util.ItemClickSupport;
import com.arwandar.myseriesaddict.data.model.User;
import com.arwandar.myseriesaddict.data.service.CallManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class FriendsActivity extends AppCompatActivity {

    protected List<User> mUsers = new ArrayList<>();

    @Bind(R.id.friends_recycler_view)
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    FriendsAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mUsers = CallManager.getFriendsList().getUsers();

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new FriendsAdapter(this, mUsers);
        mRecyclerView.setAdapter(mAdapter);

        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                String toDiplay = mUsers.get(position).getmLogin();
                Snackbar sncack = Snackbar.make(recyclerView, toDiplay, Snackbar.LENGTH_LONG);
                sncack.show();
            }
        });

        ItemClickSupport.addTo(mRecyclerView).setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
                String toDisplay = mUsers.get(position).getmLogin();
                Snackbar snack = Snackbar.make(recyclerView, toDisplay, Snackbar.LENGTH_LONG);
                snack.show();
                return false;
            }
        });
    }
}
