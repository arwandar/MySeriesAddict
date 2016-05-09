package com.arwandar.myseriesaddict.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.api.converter.UsersConverter;
import com.arwandar.myseriesaddict.api.dto.UsersDTO;
import com.arwandar.myseriesaddict.api.model.User;
import com.arwandar.myseriesaddict.api.service.CallManager;
import com.arwandar.myseriesaddict.ui.ItemClickSupport;
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
    protected ProgressDialog progress;
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

        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                String toDiplay = mUsers.get(position).getmLogin();
                Snackbar snack = Snackbar.make(recyclerView, toDiplay, Snackbar.LENGTH_LONG);
                snack.show();
            }
        });

        getContent();

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

    private void getContent() {
        progress = ProgressDialog.show(FriendsActivity.this, "Patientez",
                "Chargement de la liste", true);

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
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<UsersDTO> call, Throwable t) {
                showError();
            }
        });
    }

}
