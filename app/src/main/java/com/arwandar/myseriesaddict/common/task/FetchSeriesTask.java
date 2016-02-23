package com.arwandar.myseriesaddict.common.task;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by Arwandar on 23/02/2016.
 */
public class FetchSeriesTask extends AsyncTask<String, Void, String> {
    public static final String HTTP_RESPONSE = "httpResponse";
    private Context mContext;
    private String mAction;

    public FetchSeriesTask(Context context, String action) {
        this.mContext = context;
        this.mAction = action;
    }

    @Override
    protected String doInBackground(String... params) {
        String result = "";
        Uri.Builder builder = new Uri.Builder()
                .scheme("http")
                .authority("api.betaseries.com")
                .path("members/infos")
                .appendQueryParameter("v", "2.4")
                .appendQueryParameter("key", "A93691358C05")
                .appendQueryParameter("id", "228210")
                .appendQueryParameter("only", "shows");

        String callUrl = builder.build().toString();
        RequestQueue queue = Volley.newRequestQueue(this.mContext);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, callUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Intent intent = new Intent(mAction);
                        intent.putExtra(HTTP_RESPONSE, response);
                        mContext.sendBroadcast(intent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ERROR API BETASERIE");
            }
        });

        stringRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });

        queue.add(stringRequest);

        return result;
    }
}
