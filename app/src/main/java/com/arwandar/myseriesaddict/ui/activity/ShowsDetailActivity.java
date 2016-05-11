package com.arwandar.myseriesaddict.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
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

public class ShowsDetailActivity extends CustomSwipeAndShakableActivity {

    @Bind(R.id.swipeRefreshLayout)
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    Shows mShows;
    @Bind(R.id.shows_detail_archived)
    Switch mSwitchArchived;
    @Bind(R.id.shows_detail_favorite)
    Switch mSwitchFavorite;
    @Bind((R.id.tabs))
    TabLayout mTabLayout;

    String showsId;

    /**
     * flag pour interdire les appels parallèles
     */
    private boolean alreadyArchivedCall = true, alreadyFavoriteCall = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shows_detail);

        initActivity();

        mTabLayout.addTab(mTabLayout.newTab().setText("Détails"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Episodes"));
        mTabLayout.setTabGravity(mTabLayout.GRAVITY_FILL);

        showsId = getIntent().getStringExtra("showsId");

        getContent();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getContent();
            }
        });
    }

    /**
     * appel au webservice pour recuperer les données
     */
    protected void getContent() {
        mSwipeRefreshLayout.setRefreshing(true);
        getDetails();
        getEpisodes();
    }

    private void getEpisodes() {
    }

    private void getDetails() {
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
    private void setUI() {
        ImageView image = (ImageView) findViewById(R.id.shows_detail_image);
        Picasso.with(ShowsDetailActivity.this).load(mShows.getmImages().getmShow()).into(image);

        //TODO mettre à jour les fragments
    }


}
