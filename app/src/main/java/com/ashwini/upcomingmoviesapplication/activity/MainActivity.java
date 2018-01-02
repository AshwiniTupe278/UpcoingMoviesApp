package com.ashwini.upcomingmoviesapplication.activity;

import android.content.Context;
import android.content.Intent;
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
import com.ashwini.upcomingmoviesapplication.async.UpcomingDataAsync;
import com.ashwini.upcomingmoviesapplication.interfaces.OnDataListener;
import com.ashwini.upcomingmoviesapplication.model.MovieModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements OnDataListener {
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

       initialisation();
    }

    private void initialisation() {
        textViewToolbar = findViewById(R.id.toolbar_title);
        textViewToolbar.setText(R.string.upcoming_title);

        movieRecyclerView = findViewById(R.id.rvMovieList);
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        movieList = new ArrayList<>();

        movieAdapter = new MovieDataAdapter(this,movieList);
        callUpcomingMovieData();

        movieRecyclerView.setAdapter(movieAdapter);


    }

    private void callUpcomingMovieData() {
        new UpcomingDataAsync().getUpcomingMoviesData(MainActivity.this,this);
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
            Intent intentAbout = new Intent(mContext,AboutInfoActivity.class);
            startActivity(intentAbout);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDataListener(String message, boolean flag) {
        if (flag)
        {
            try {
                JSONObject object = new JSONObject(message);

                JSONArray array = object.getJSONArray("results");

                for (int i=0;i<array.length();i++)
                {
                    JSONObject jsonObject = array.getJSONObject(i);

                    MovieModel mModel = new MovieModel();
                    mModel.setMovieName(jsonObject.getString("title"));
                    mModel.setMovieAdultFlag(jsonObject.getBoolean("adult"));
                    mModel.setMovieReleaseDate(jsonObject.getString("release_date"));
                    mModel.setPosterPath(jsonObject.getString("poster_path"));
                    movieList.add(mModel);
                }

                movieAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else
        {
            Toast.makeText(mContext,"error",Toast.LENGTH_SHORT).show();
        }
    }
}
