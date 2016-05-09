package com.arwandar.myseriesaddict.ui.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.api.SharedPrefsSingleton;
import com.arwandar.myseriesaddict.api.converter.ShowDisplayComplexConverter;
import com.arwandar.myseriesaddict.api.dto.ShowDisplayComplexDTO;
import com.arwandar.myseriesaddict.api.model.Shows;
import com.arwandar.myseriesaddict.api.service.CallManager;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowsDetailActivity extends CustomActivity {
    protected ProgressDialog progress;
    Shows mShows;

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
                    if (response.code() == 400) {
                        SharedPrefsSingleton.setAccessToken("");
                        Intent intent = new Intent(ShowsDetailActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<ShowDisplayComplexDTO> call, Throwable t) {
                Toast.makeText(ShowsDetailActivity.this, "Pas d'accès à internet, veuillez réessayer plus tard.", Toast.LENGTH_SHORT).show();
                progress.dismiss();
                AlertDialog.Builder builder = new AlertDialog.Builder(ShowsDetailActivity.this);
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

    public void setUI() {
        ((TextView) findViewById(R.id.shows_detail_title)).setText(mShows.getmTitle());
        String shows_detail_pending = mShows.getmStatus() + " ( " + mShows.getmEpisodes() + " épisode(s))";
        ((TextView) findViewById(R.id.shows_detail_pending)).setText(shows_detail_pending);
        ImageView image = (ImageView) findViewById(R.id.shows_detail_image);
        Picasso.with(ShowsDetailActivity.this).load(mShows.getmImages().getmShow()).into(image);
        ((TextView) findViewById(R.id.shows_detail_description)).setText(mShows.getmDescription());


    }
}
