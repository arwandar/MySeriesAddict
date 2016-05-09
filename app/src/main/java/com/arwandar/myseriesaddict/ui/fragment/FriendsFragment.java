package com.arwandar.myseriesaddict.ui.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.api.SharedPrefsSingleton;
import com.arwandar.myseriesaddict.api.converter.UsersConverter;
import com.arwandar.myseriesaddict.api.dto.UsersDTO;
import com.arwandar.myseriesaddict.api.model.User;
import com.arwandar.myseriesaddict.api.service.CallManager;
import com.arwandar.myseriesaddict.api.service.NetworkTester;
import com.arwandar.myseriesaddict.ui.ItemClickSupport;
import com.arwandar.myseriesaddict.ui.activity.LoginActivity;
import com.arwandar.myseriesaddict.ui.adpater.FriendsAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arwandar on 07/05/2016.
 */
public class FriendsFragment extends Fragment {
    protected final List<User> mUsers = new ArrayList<>();
    protected ProgressDialog progress;
    @Bind(R.id.friends_recycler_view)
    RecyclerView mRecyclerView;
    FriendsAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_friends, container, false);
        ButterKnife.bind(this, view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new FriendsAdapter(getActivity(), mUsers);
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
        return view;
    }

    private void getContent() {
//        progress = ProgressDialog.show(getActivity(), "Patientez",
//            "Chargement de la liste", true);

        if (!NetworkTester.checkNetwork(this.getContext())) {
            System.out.print("Pas d'internet.");
        }


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
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<UsersDTO> call, Throwable t) {
//                progress.dismiss();
//                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                builder.setMessage(R.string.dialog_message_error)
//                        .setTitle(R.string.dialog_title_error);
//                builder.setNeutralButton(R.string.ok_error, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // User clicked OK button
//                    }
//                });
//                AlertDialog dialog = builder.create();
            }
        });
    }

}
