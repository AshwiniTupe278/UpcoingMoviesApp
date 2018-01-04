package com.ashwini.upcomingmoviesapplication.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ashwini.upcomingmoviesapplication.R;
import com.ashwini.upcomingmoviesapplication.adapter.ViewPagerAdapter;
import com.ashwini.upcomingmoviesapplication.async.UpcomingDataAsync;
import com.ashwini.upcomingmoviesapplication.interfaces.OnMovieDetailsListener;
import com.ashwini.upcomingmoviesapplication.utility.ConnectionManager;
import com.ashwini.upcomingmoviesapplication.utility.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MovieDetailsActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, OnMovieDetailsListener {

    private static String MOVIE_ID;
    private static String MOVIE_NAME = null;
    private final Context mContext = MovieDetailsActivity.this;
    ArrayList<String> movieImageList = new ArrayList<>();
    private TextView textViewToolbar;
    private ViewPager viewPagerMovieImages;
    private LinearLayout sliderDotsPanel;
    private Integer dotCount = 5;
    private ImageView[] dotsImage;
    private ViewPagerAdapter adapter;
    private TextView tv_movieTitle, tv_movieOverview;
    private RatingBar movieRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        initialisation();
    }

    private void initialisation() {

        Bundle bundle = getIntent().getExtras();
        MOVIE_ID = bundle.getString("MovieId");
        MOVIE_NAME = bundle.getString("MovieTitle");

        textViewToolbar = findViewById(R.id.toolbar_title);
        textViewToolbar.setText(MOVIE_NAME);

        viewPagerMovieImages = findViewById(R.id.movieViewPager);
        sliderDotsPanel = findViewById(R.id.sliderDots);

        adapter = new ViewPagerAdapter(mContext, movieImageList);
        viewPagerMovieImages.setAdapter(adapter);


        dotsImage = new ImageView[dotCount];
        for (int i = 0; i < dotCount; i++) {
            dotsImage[i] = new ImageView(this);
            dotsImage[i].setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.non_active_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotsPanel.addView(dotsImage[i], params);
        }
        dotsImage[0].setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.active_dot));

        viewPagerMovieImages.addOnPageChangeListener(this);

        tv_movieTitle = findViewById(R.id.tv_movieTitle);
        tv_movieOverview = findViewById(R.id.tv_movieOverview);
        movieRating = findViewById(R.id.movieRatingBar);
        
        callMovieDetailsDataParsing();
    }

    private void callMovieDetailsDataParsing() {
        new UpcomingDataAsync().getMovieDetails(mContext, MOVIE_ID, this);
    }

    private void callDataParsing() {
        new UpcomingDataAsync().getImagesofUpcomingMovie(mContext, MOVIE_ID, this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < dotCount; i++) {
            dotsImage[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));
        }

        dotsImage[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onMovieDetailsListener(String message, String indicator, boolean flag) {
        if (flag && indicator.equalsIgnoreCase("Images")) {
            Log.d(indicator, message);
            callImageJsonParsing(message);
        } else if (flag && indicator.equalsIgnoreCase("Details")) {
            Log.d(indicator, message);
            callDetailsJsonParsing(message);
        } else {
            Toast.makeText(mContext, "Some error is there", Toast.LENGTH_SHORT).show();
        }
    }

    private void callDetailsJsonParsing(String message) {
        try {
            JSONObject object = new JSONObject(message);
            tv_movieOverview.setText(object.getString("overview"));
            tv_movieTitle.setText(object.getString("title"));
//            movieRating.setRating(Float.valueOf(object.getString("popularity")));

            float rating =( Float.valueOf(object.getString("popularity")) *5) / 1000;
            movieRating.setRating(rating);
            Log.d("Ratimg",String.valueOf(rating));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void callImageJsonParsing(String message) {
        try {
            JSONObject object = new JSONObject(message);
            JSONArray array = object.getJSONArray("posters");


            for (int i = 0; i < dotCount; i++) {
                String imagePath = array.getJSONObject(i).getString("file_path");
                Log.d("path", Constant.MOVIE_POSTER_IMAGE + imagePath);
                movieImageList.add(Constant.MOVIE_POSTER_IMAGE + imagePath);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter.notifyDataSetChanged();
    }
}
