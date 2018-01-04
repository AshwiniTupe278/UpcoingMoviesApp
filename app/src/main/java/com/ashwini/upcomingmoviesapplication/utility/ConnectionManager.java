package com.ashwini.upcomingmoviesapplication.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by softdotcom-7 on 3/1/18.
 */

public class ConnectionManager {
    public boolean isNetworkConnected(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager)context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

}