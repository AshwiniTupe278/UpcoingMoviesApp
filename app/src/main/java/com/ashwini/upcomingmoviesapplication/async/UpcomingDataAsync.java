package com.ashwini.upcomingmoviesapplication.async;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ashwini.upcomingmoviesapplication.activity.MovieDetailsActivity;
import com.ashwini.upcomingmoviesapplication.interfaces.OnDataListener;
import com.ashwini.upcomingmoviesapplication.interfaces.OnMovieDetailsListener;
import com.ashwini.upcomingmoviesapplication.utility.Constant;

import org.json.JSONObject;

/**
 * Created by softdotcom-7 on 28/12/17.
 */

public class UpcomingDataAsync {

    public void getUpcomingMoviesData(Context mContext,final OnDataListener listener)
    {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Constant.UPCOMING_MOVIES_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("response",response.toString());
                listener.onDataListener(response.toString(),true);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("response",error.toString());
                listener.onDataListener(error.toString(),false);
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(mContext).add(request);
    }

    public void getImagesofUpcomingMovie(Context mContext ,String movieId,final OnMovieDetailsListener listener)
    {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Constant.MAIN_URL+movieId+"/images"+Constant.API_KEY, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("response",response.toString());
                listener.onMovieDetailsListener(response.toString(),"Images",true);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("response",error.toString());
                listener.onMovieDetailsListener(error.toString(),"Images",false);
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(mContext).add(request);
    }

    public void getMovieDetails(Context mContext, String movieId, final OnMovieDetailsListener listener) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Constant.MAIN_URL+movieId+Constant.API_KEY, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("response",response.toString());
                listener.onMovieDetailsListener(response.toString(),"Details",true);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("response",error.toString());
                listener.onMovieDetailsListener(error.toString(),"Details",false);
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(mContext).add(request);
    }
}
