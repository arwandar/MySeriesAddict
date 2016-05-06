package com.arwandar.myseriesaddict.ui.fragment.old;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.arwandar.myseriesaddict.common.task.old.FetchSeriesTask;
import com.arwandar.myseriesaddict.model.old.Series;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Arwandar on 22/02/2016.
 */
public class EndedSeriesFragment extends SeriesFragment {
    public EndedSeriesFragment() {
        ACTION_FOR_INTENT_CALLBACK = "INTENT_CALLBACK_FOR_ENDED";

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (progress != null) {
                    progress.dismiss();
                }
                mContent = intent.getStringExtra(FetchSeriesTask.HTTP_RESPONSE);
                try {
                    Series.buildListFromJSONArray(mSeries, new JSONObject(mContent).getJSONObject("member").getJSONArray("shows"), "Ended");
                    //Collections.sort(mSeries);
                    mAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
