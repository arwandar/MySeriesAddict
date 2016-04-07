package com.arwandar.myseriesaddict.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.model.Series;

public class DetailActivity extends AppCompatActivity {

    private Series mSeries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Details");
            actionBar.setDisplayShowTitleEnabled(true);
        }

        Bundle mBundle = getIntent().getExtras();
        mSeries = (Series) mBundle.get("seriesSelected");

        TextView seriesName = (TextView) this.findViewById(R.id.series_detail_name);
        TextView seriesPlot = (TextView) this.findViewById(R.id.series_detail_plot);
        View seriesPicture = this.findViewById(R.id.series_detail_color);

        seriesName.setText(mSeries.getmName());
        seriesPlot.setText(mSeries.getmPlot());
        seriesPicture.setBackgroundColor(mSeries.getmColor());




        Button loginButton = (Button) findViewById(R.id.loginbutton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
