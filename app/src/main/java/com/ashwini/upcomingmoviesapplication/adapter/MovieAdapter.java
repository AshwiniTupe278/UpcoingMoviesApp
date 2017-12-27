package com.ashwini.upcomingmoviesapplication.adapter;

import android.content.Context;
import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ashwini.upcomingmoviesapplication.R;
import com.ashwini.upcomingmoviesapplication.model.Movies;

import java.util.List;

/**
 * Created by softdotcom-7 on 26/12/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{

    private List<Movies> moviesList;
    private Context mContext;


    public MovieAdapter(List<Movies> moviesList, Context mContext) {
        this.moviesList = moviesList;
        this.mContext = mContext;
    }


    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView  = LayoutInflater.from(mContext).inflate(R.layout.movie_list_row,parent,false);
        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movies movieObj = moviesList.get(position);
        holder.tvMovieTitle.setText(movieObj.getMovieName());
        holder.tvMovieAdult.setText(movieObj.getMovieAdult());
        holder.tvMovieReleaseDate.setText(movieObj.getMovieReleaseDate());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        TextView tvMovieTitle,tvMovieReleaseDate,tvMovieAdult;
        public MovieViewHolder(View itemView) {
            super(itemView);
            tvMovieTitle = itemView.findViewById(R.id.tvMovieName);
            tvMovieReleaseDate = itemView.findViewById(R.id.tvMovieReleaseDate);
            tvMovieAdult = itemView.findViewById(R.id.tvMovieAdult);
        }
    }
}
