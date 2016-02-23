package com.arwandar.myseriesaddict.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.arwandar.myseriesaddict.common.task.FetchSeriesTask;
import com.arwandar.myseriesaddict.model.Series;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Arwandar on 23/02/2016.
 */
public class ContinuingSeriesFragment extends SeriesFragment {
    public ContinuingSeriesFragment() {
        ACTION_FOR_INTENT_CALLBACK = "INTENT_CALLBACK_FOR_CONTINUING";
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (progress != null) {
                    progress.dismiss();
                }
                mContent = intent.getStringExtra(FetchSeriesTask.HTTP_RESPONSE);
                try {
                    Series.buildListFromJSONArray(mSeries, new JSONObject(mContent).getJSONObject("member").getJSONArray("shows"), "Continuing");
                    //Collections.sort(mSeries);
                    mAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
