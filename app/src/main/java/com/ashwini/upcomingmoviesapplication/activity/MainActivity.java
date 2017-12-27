package com.ashwini.upcomingmoviesapplication.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.ashwini.upcomingmoviesapplication.R;
import com.ashwini.upcomingmoviesapplication.adapter.MovieDataAdapter;
import com.ashwini.upcomingmoviesapplication.model.MovieModel;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private TextView textViewToolbar;
    private  Context mContext = MainActivity.this;

   private ArrayList<MovieModel> movieList;
   private RecyclerView movieRecyclerView;
   private MovieDataAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        textViewToolbar = findViewById(R.id.toolbar_title);
        textViewToolbar.setText(R.string.upcoming_title);

        movieRecyclerView = findViewById(R.id.rvMovieList);
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        movieList = new ArrayList<>();
        movieList.add(new MovieModel("aaa","aaa",false));

        movieList.add(new MovieModel("aaa","aaa",true));

        movieAdapter = new MovieDataAdapter(this,movieList);

        movieRecyclerView.setAdapter(movieAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_info) {
            Toast.makeText(mContext,"edgfjh",Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
