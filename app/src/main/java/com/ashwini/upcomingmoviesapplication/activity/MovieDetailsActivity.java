package com.ashwini.upcomingmoviesapplication.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ashwini.upcomingmoviesapplication.R;
import com.ashwini.upcomingmoviesapplication.adapter.ViewPagerAdapter;
import com.ashwini.upcomingmoviesapplication.interfaces.OnMovieDetailsListener;
import com.ashwini.upcomingmoviesapplication.async.UpcomingDataAsync;

import java.util.ArrayList;

public class MovieDetailsActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, OnMovieDetailsListener {

    private static String MOVIE_ID ;
    private static String MOVIE_NAME = null;
    ArrayList<String> movieImageList = new ArrayList<>();
    private TextView textViewToolbar;
    private ViewPager viewPagerMovieImages;
    private LinearLayout sliderDotsPanel;
    private Integer dotCount;
    private ImageView[] dotsImage;
    private final Context mContext = MovieDetailsActivity.this;

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

        movieImageList.add("https://static.pexels.com/photos/67636/rose-blue-flower-rose-blooms-67636.jpeg");
        movieImageList.add("https://static.pexels.com/photos/34950/pexels-photo.jpg");


        ViewPagerAdapter adapter = new ViewPagerAdapter(mContext, movieImageList);
        viewPagerMovieImages.setAdapter(adapter);


        dotCount = adapter.getCount();

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

        callDataParsing();

    }

    private void callDataParsing() {
        new UpcomingDataAsync().getImagesofUpcomingMovie(mContext,MOVIE_ID,this);
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
        if(flag && indicator.equalsIgnoreCase("Images"))
        {
            Log.d("Response : " ,message);
        }
        else if (flag && indicator.equalsIgnoreCase("details"))
        {
            Log.d("Response : ",message);
        }
        else {
            Toast.makeText(mContext,"Some error is there",Toast.LENGTH_SHORT).show();
        }

    }
}
