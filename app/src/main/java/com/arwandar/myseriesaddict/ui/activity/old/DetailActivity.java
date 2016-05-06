package com.arwandar.myseriesaddict.ui.activity.old;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.model.old.Series;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @Bind(R.id.series_detail_name)
    TextView seriesName;
    @Bind(R.id.series_detail_plot)
    TextView seriesPlot;
    @Bind(R.id.series_detail_color)
    View seriesPicture;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private Series mSeries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.old_activity_detail);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Details");
            actionBar.setDisplayShowTitleEnabled(true);
        }

        Bundle mBundle = getIntent().getExtras();
        mSeries = (Series) mBundle.get("seriesSelected");

        seriesName.setText(mSeries.getmName());
        seriesPlot.setText(mSeries.getmPlot());
        seriesPicture.setBackgroundColor(mSeries.getmColor());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
