package com.ashwini.upcomingmoviesapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ashwini.upcomingmoviesapplication.R;
import com.ashwini.upcomingmoviesapplication.model.MovieModel;

import java.util.List;

/**
 * Created by softdotcom-7 on 27/12/17.
 */

public class MovieDataAdapter extends RecyclerView.Adapter<MovieDataAdapter.MovieDataHolder> {

    private Context mContext;
    private List<MovieModel> movieList;

    public MovieDataAdapter(Context mContext, List<MovieModel> movieList) {
        this.mContext = mContext;
        this.movieList = movieList;
    }

    @Override
    public MovieDataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_single_row,parent,false);
        MovieDataHolder holder = new MovieDataHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MovieDataHolder holder, int position) {
        MovieModel movieObject = movieList.get(position);
        holder.tvMovieName.setText(movieObject.getMovieName());
        holder.tvReleaseDate.setText(movieObject.getMovieReleaseDate());

        if (movieObject.isMovieAdultFlag())
        {
            holder.tvAdultFlag.setText("(A)");
        }
        else
        {
            holder.tvAdultFlag.setText("(U/A)");
        }

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieDataHolder extends RecyclerView.ViewHolder {

        TextView tvMovieName,tvReleaseDate,tvAdultFlag;

        public MovieDataHolder(View itemView) {
            super(itemView);
            tvMovieName = itemView.findViewById(R.id.tvMovieName);
            tvReleaseDate = itemView.findViewById(R.id.tvReleaseDate);
            tvAdultFlag = itemView.findViewById(R.id.tvAdultFlag);
        }
    }
}
