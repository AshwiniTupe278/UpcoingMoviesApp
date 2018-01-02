package com.ashwini.upcomingmoviesapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ashwini.upcomingmoviesapplication.R;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView textViewToolbar;
    private static  String MOVIE_ID =null;
    private static  String MOVIE_NAME =null;

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
    }
}
