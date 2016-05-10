package com.arwandar.myseriesaddict.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.api.converter.ShowDisplayComplexConverter;
import com.arwandar.myseriesaddict.api.dto.ShowDisplayComplexDTO;
import com.arwandar.myseriesaddict.api.model.Shows;
import com.arwandar.myseriesaddict.api.service.CallManager;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.OnCheckedChanged;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowsDetailActivity extends CustomActivity {

    @Bind(R.id.swipeRefreshLayout)
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    Shows mShows;
    @Bind(R.id.shows_detail_archived)
    Switch mSwitchArchived;
    @Bind(R.id.shows_detail_favorite)
    Switch mSwitchFavorite;
    /**
     * flag pour interdire les appels parallèles
     */
    private boolean alreadyArchivedCall = true, alreadyFavoriteCall = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shows_detail);

        initActivity();

        getContent(getIntent().getStringExtra("showsId"), false);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                getContent(mShows.getmId(), true);
            }
        });
    }

    /**
     * appel au webservice pour recuperer les données
     */
    private void getContent(String showsId, boolean isRefreshing) {
        mSwipeRefreshLayout.setRefreshing(true);
        CallManager.getShowDisplayAsync(showsId, new Callback<ShowDisplayComplexDTO>() {
            @Override
            public void onResponse(Call<ShowDisplayComplexDTO> call,
                    Response<ShowDisplayComplexDTO> response) {
                if (response.isSuccessful()) {
                    ShowDisplayComplexConverter converter = new ShowDisplayComplexConverter();
                    mShows = converter.convertDtoToShowDisplayComplex(response.body()).getmShow();
                    setUI();
                } else {
                    showErrorLogin(response.code());
                }
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<ShowDisplayComplexDTO> call, Throwable t) {
                mSwipeRefreshLayout.setRefreshing(false);
                showError();
            }
        });
    }

    /**
     * mise à jour des champs avec les infos recupérées
     */
    public void setUI() {
        ((TextView) findViewById(R.id.shows_detail_title)).setText(mShows.getmTitle());
        String shows_detail_pending = mShows.getmStatus() + " ("
                + mShows.getmUser().getmRemaining() + " non-vu(s) épisodes sur "
                + mShows.getmEpisodes() + ")";
        ((TextView) findViewById(R.id.shows_detail_pending)).setText(shows_detail_pending);
        ImageView image = (ImageView) findViewById(R.id.shows_detail_image);
        Picasso.with(ShowsDetailActivity.this).load(mShows.getmImages().getmShow()).into(image);
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
                                    R.string.mark_no_favorite_ok,
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
                                    R.string.mark_no_archived_ok,
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
                                    R.string.mark_archived_ok,
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
}
