package com.arwandar.myseriesaddict.common.adpater;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.model.Series;

import java.util.List;

/**
 * Created by Arwandar on 22/02/2016.
 */
public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.ViewHolder> {

    private Activity mActivity;
    private List<Series> mSeriesList;

    public SeriesAdapter(Activity activity, List<Series> seriesList) {
        mActivity = activity;
        mSeriesList = seriesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.series_small_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Series series = mSeriesList.get(position);

        holder.mColorView.setBackgroundColor(series.getmColor());
        holder.mNameView.setText(series.getSmallName());
        holder.mPlotView.setText(series.getSmallPlot());
    }

    @Override
    public int getItemCount() {
        return mSeriesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private View mColorView;
        private TextView mNameView;
        private TextView mPlotView;

        public ViewHolder(View v) {
            super(v);
            mColorView = v.findViewById(R.id.series_color);
            mNameView = (TextView) v.findViewById(R.id.series_name);
            mPlotView = (TextView) v.findViewById(R.id.series_plot);
        }
    }
}
