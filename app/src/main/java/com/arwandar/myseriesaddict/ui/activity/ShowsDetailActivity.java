package com.arwandar.myseriesaddict.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
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
    protected ProgressDialog progress;
    Shows mShows;
    @Bind(R.id.shows_detail_archived)
    Switch mSwitchArchived;
    @Bind(R.id.shows_detail_favorite)
    Switch mSwitchFavorite;
    private boolean alreadyArchivedCall = true, alreadyFavoriteCall = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shows_detail);

        initActivity();

        getContent(getIntent().getStringExtra("showsId"));


    }

    private void getContent(String showsId) {
        progress = ProgressDialog.show(ShowsDetailActivity.this, "Patientez",
                "Chargement de la série", true);
        CallManager.getShowDisplayAsync(showsId, new Callback<ShowDisplayComplexDTO>() {
            @Override
            public void onResponse(Call<ShowDisplayComplexDTO> call, Response<ShowDisplayComplexDTO> response) {
                if (response.isSuccessful()) {
                    ShowDisplayComplexConverter converter = new ShowDisplayComplexConverter();
                    mShows = converter.convertDtoToShowDisplayComplex(response.body()).getmShow();
                    setUI();
                } else {
                    showErrorLogin(response.code());
                }
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<ShowDisplayComplexDTO> call, Throwable t) {
                progress.dismiss();
                showError();
            }
        });
    }

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

    @OnCheckedChanged(R.id.shows_detail_favorite)
    public void setFavorite() {
        if (!alreadyFavoriteCall) {
            alreadyFavoriteCall = true;
            if (mSwitchFavorite.isChecked()) {
                CallManager.markShowAsFavoriteAsync(mShows.getmId(), new Callback<ShowDisplayComplexDTO>() {
                    @Override
                    public void onResponse(Call<ShowDisplayComplexDTO> call, Response<ShowDisplayComplexDTO> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(ShowsDetailActivity.this, "La série a été marquée comme favorite.", Toast.LENGTH_SHORT).show();
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
            } else {
                CallManager.deleteShowFromFavoriteAsync(mShows.getmId(), new Callback<ShowDisplayComplexDTO>() {
                    @Override
                    public void onResponse(Call<ShowDisplayComplexDTO> call, Response<ShowDisplayComplexDTO> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(ShowsDetailActivity.this, "La série a été enlevée des favorites.", Toast.LENGTH_SHORT).show();
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
        }
    }

    @OnCheckedChanged(R.id.shows_detail_archived)
    public void setArchived() {
        if (!alreadyArchivedCall) {
            alreadyArchivedCall = true;
            if (mSwitchArchived.isChecked()) {
                CallManager.markShowAsArchivedAsync(mShows.getmId(), new Callback<ShowDisplayComplexDTO>() {
                    @Override
                    public void onResponse(Call<ShowDisplayComplexDTO> call, Response<ShowDisplayComplexDTO> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(ShowsDetailActivity.this, "La série a été marquée comme archivée.", Toast.LENGTH_SHORT).show();
                            mSwitchArchived.setChecked(true);
                            alreadyArchivedCall = false;
                        } else {
                            showErrorLogin(response.code());
                            alreadyArchivedCall = false;
                        }
                    }

                    @Override
                    public void onFailure(Call<ShowDisplayComplexDTO> call, Throwable t) {
                        showError();
                        alreadyArchivedCall = false;
                    }
                });
            } else {
                CallManager.deleteShowFromArchivedAsync(mShows.getmId(), new Callback<ShowDisplayComplexDTO>() {
                    @Override
                    public void onResponse(Call<ShowDisplayComplexDTO> call, Response<ShowDisplayComplexDTO> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(ShowsDetailActivity.this, "La série a été enlevée des archivées.", Toast.LENGTH_SHORT).show();
                            mSwitchArchived.setChecked(false);
                            alreadyArchivedCall = false;
                        } else {
                            showErrorLogin(response.code());
                            alreadyArchivedCall = false;
                        }
                    }

                    @Override
                    public void onFailure(Call<ShowDisplayComplexDTO> call, Throwable t) {
                        showError();
                        alreadyArchivedCall = false;
                    }
                });
            }
        }
    }
}
