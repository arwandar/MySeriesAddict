package com.arwandar.myseriesaddict.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.api.converter.UsersConverter;
import com.arwandar.myseriesaddict.api.dto.UsersDTO;
import com.arwandar.myseriesaddict.api.model.User;
import com.arwandar.myseriesaddict.api.service.CallManager;
import com.arwandar.myseriesaddict.ui.adpater.FriendsAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendsActivity extends CustomActivity {

    protected final List<User> mUsers = new ArrayList<>();
    @Bind(R.id.swipeRefreshLayout)
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.friends_recycler_view)
    RecyclerView mRecyclerView;
    FriendsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        initActivity();

        ButterKnife.bind(this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new FriendsAdapter(this, mUsers);
        mRecyclerView.setAdapter(mAdapter);

        getContent();
    }

    /**
     * appel au webservice pour recuperer les données
     */
    private void getContent() {
        mSwipeRefreshLayout.setRefreshing(true);
        CallManager.getFriendsListAsync(new Callback<UsersDTO>() {
            @Override
            public void onResponse(Call<UsersDTO> call, Response<UsersDTO> response) {
                if (response.isSuccessful()) {
                    UsersConverter converter = new UsersConverter();
                    mUsers.clear();
                    for (User us : converter.convertDtoToUsers(response.body()).getUsers()) {
                        mUsers.add(us);
                    }
                    Collections.sort(mUsers);
                    mAdapter.notifyDataSetChanged();
                } else {
                    showErrorLogin(response.code());
                }
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<UsersDTO> call, Throwable t) {
                showError();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
