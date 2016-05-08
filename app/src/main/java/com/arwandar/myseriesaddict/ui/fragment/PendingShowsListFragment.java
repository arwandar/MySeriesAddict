package com.arwandar.myseriesaddict.ui.fragment;

import android.content.Intent;

import com.arwandar.myseriesaddict.api.SharedPrefsSingleton;
import com.arwandar.myseriesaddict.api.converter.MemberComplexConverter;
import com.arwandar.myseriesaddict.api.dto.MemberComplexDTO;
import com.arwandar.myseriesaddict.api.model.Shows;
import com.arwandar.myseriesaddict.api.service.CallManager;
import com.arwandar.myseriesaddict.ui.activity.LoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arwandar on 05/05/2016.
 */
public class PendingShowsListFragment extends ShowsListFragment {
    @Override
    void getContent() {
        CallManager.getMemberInfosAsync(new Callback<MemberComplexDTO>() {
            @Override
            public void onResponse(Call<MemberComplexDTO> call, Response<MemberComplexDTO> response) {
                progress.dismiss();
                if (response.isSuccessful()) {
                    MemberComplexConverter converter = new MemberComplexConverter();
                    mShows.clear();
                    for (Shows shows : converter.convertDtoToMember(response.body()).getUser().getmShows()) {
                        if (shows.getmUser().getmArchived() == "false") mShows.add(shows);
                    }
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
            public void onFailure(Call<MemberComplexDTO> call, Throwable t) {

            }
        });
    }
}
