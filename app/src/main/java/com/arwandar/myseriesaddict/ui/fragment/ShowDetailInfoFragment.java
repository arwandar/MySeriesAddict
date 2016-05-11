package com.arwandar.myseriesaddict.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.api.dto.ShowDisplayComplexDTO;
import com.arwandar.myseriesaddict.api.service.CallManager;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by olivi on 11/05/2016.
 */
public class ShowDetailInfoFragment extends Fragment {

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_show_detail_info, container, false);
        ButterKnife.bind(this, view);

        //getActivity().setTitle(title);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ShowsAdapter(getActivity(), mShows);
        mRecyclerView.setAdapter(mAdapter);

        return  view;
    }



    public void setUI() {
        ((TextView) findViewById(R.id.shows_detail_title)).setText(mShows.getmTitle());
        String shows_detail_pending = mShows.getmStatus() + " ("
                + mShows.getmUser().getmRemaining() + " non-vu(s) épisodes sur "
                + mShows.getmEpisodes() + ")";
        ((TextView) findViewById(R.id.shows_detail_pending)).setText(shows_detail_pending);
        ((TextView) findViewById(R.id.shows_detail_description)).setText(mShows.getmDescription());

        mSwitchArchived.setChecked(mShows.getmUser().getmArchived().equals("true"));
        mSwitchFavorite.setChecked(mShows.getmUser().getmFavorited().equals("true"));
        alreadyArchivedCall = false;
        alreadyFavoriteCall = false;
    }



    /**
     * gestion du swipe favori
     */
    @OnCheckedChanged(R.id.shows_detail_favorite)
    public void setFavorite() {
        //gestion de l'unicité de l'appel
        if (!alreadyFavoriteCall) {
            alreadyFavoriteCall = true;
            if (mSwitchFavorite.isChecked()) {
                markAsFavorite();
            } else {
                markAsNoFavorite();
            }
        }
    }

    /**
     * appel au WS pour marquer la série comme favorite
     */
    private void markAsFavorite() {
        CallManager.markShowAsFavoriteAsync(mShows.getmId(),
                new Callback<ShowDisplayComplexDTO>() {
                    @Override
                    public void onResponse(Call<ShowDisplayComplexDTO> call,
                                           Response<ShowDisplayComplexDTO> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(ShowsDetailActivity.this,
                                    R.string.mark_favorite_ok_message,
                                    Toast.LENGTH_SHORT).show();
                            mSwitchFavorite.setChecked(true);
                            alreadyFavoriteCall = false;
                        } else {
                            showErrorLogin(response.code());
                            alreadyFavoriteCall = false;
                        }
                    }

                    @Override
                    public void onFailure(Call<ShowDisplayComplexDTO> call, Throwable t) {
                        showError();
                        alreadyFavoriteCall = false;
                    }
                });
    }

    /**
     * appel au WS pour marquer la série comme non favorite
     */
    private void markAsNoFavorite() {
        CallManager.deleteShowFromFavoriteAsync(mShows.getmId(),
                new Callback<ShowDisplayComplexDTO>() {
                    @Override
                    public void onResponse(Call<ShowDisplayComplexDTO> call,
                                           Response<ShowDisplayComplexDTO> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(ShowsDetailActivity.this,
                                    R.string.mark_no_favorite_ok_message,
                                    Toast.LENGTH_SHORT).show();
                            mSwitchFavorite.setChecked(false);
                            alreadyFavoriteCall = false;
                        } else {
                            showErrorLogin(response.code());
                            alreadyFavoriteCall = false;
                        }
                    }

                    @Override
                    public void onFailure(Call<ShowDisplayComplexDTO> call, Throwable t) {
                        showError();
                        alreadyFavoriteCall = false;
                    }
                });
    }

    /**
     * gestion du swipe archivé
     */
    @OnCheckedChanged(R.id.shows_detail_archived)
    public void setArchived() {
        //gestion de l'unicité de l'appel
        if (!alreadyArchivedCall) {
            alreadyArchivedCall = true;
            if (mSwitchArchived.isChecked()) {
                markAsArchived();
            } else {
                markAsNoArchived();
            }
        }
    }

    /**
     * appel au WS pour marquer la série comme archivée
     */
    private void markAsNoArchived() {
        CallManager.deleteShowFromArchivedAsync(mShows.getmId(),
                new Callback<ShowDisplayComplexDTO>() {
                    @Override
                    public void onResponse(Call<ShowDisplayComplexDTO> call,
                                           Response<ShowDisplayComplexDTO> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(ShowsDetailActivity.this,
                                    R.string.mark_no_archived_ok_message,
                                    Toast.LENGTH_SHORT).show();
                            mSwitchArchived.setChecked(false);
                        } else {
                            showErrorLogin(response.code());
                        }
                        alreadyArchivedCall = false;
                    }

                    @Override
                    public void onFailure(Call<ShowDisplayComplexDTO> call, Throwable t) {
                        showError();
                        alreadyArchivedCall = false;
                    }
                });
    }

    /**
     * appel au WS pour marquer la série comme non archivée
     */
    private void markAsArchived() {//si la série n'est pas encore archivée
        CallManager.markShowAsArchivedAsync(mShows.getmId(),
                new Callback<ShowDisplayComplexDTO>() {
                    @Override
                    public void onResponse(Call<ShowDisplayComplexDTO> call,
                                           Response<ShowDisplayComplexDTO> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(ShowsDetailActivity.this,
                                    R.string.mark_archived_ok_message,
                                    Toast.LENGTH_SHORT).show();
                            mSwitchArchived.setChecked(true);
                        } else {
                            showErrorLogin(response.code());
                        }
                        alreadyArchivedCall = false;
                    }

                    @Override
                    public void onFailure(Call<ShowDisplayComplexDTO> call, Throwable t) {
                        showError();
                        alreadyArchivedCall = false;
                    }
                });
    }

