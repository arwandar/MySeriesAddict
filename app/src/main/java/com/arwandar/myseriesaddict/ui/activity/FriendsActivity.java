package com.arwandar.myseriesaddict.ui.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.api.SharedPrefsSingleton;
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setCustomNavBar();


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
                    if (response.code() == 400) {
                        SharedPrefsSingleton.setAccessToken("");
                        Intent intent = new Intent(FriendsActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<UsersDTO> call, Throwable t) {
                Toast.makeText(FriendsActivity.this, "Pas d'accès à internet, veuillez réessayer plus tard.", Toast.LENGTH_SHORT).show();
                progress.dismiss();
                AlertDialog.Builder builder = new AlertDialog.Builder(FriendsActivity.this);
                builder.setMessage(R.string.dialog_message_error)
                        .setTitle(R.string.dialog_title_error);
                builder.setNeutralButton(R.string.ok_error, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

}
