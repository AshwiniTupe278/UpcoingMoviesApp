package com.ashwini.upcomingmoviesapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ashwini.upcomingmoviesapplication.R;
import com.ashwini.upcomingmoviesapplication.activity.MainActivity;
import com.ashwini.upcomingmoviesapplication.activity.MovieDetailsActivity;
import com.ashwini.upcomingmoviesapplication.model.MovieModel;
import com.ashwini.upcomingmoviesapplication.utility.Constant;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.List;

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
        final MovieModel movieObject = movieList.get(position);
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

        String imagePath = Constant.MOVIE_POSTER_IMAGE + movieObject.getPosterPath();
        Log.d("PosterPath",imagePath);
        Picasso.with(mContext).load(imagePath).resize(200,200).into(holder.ivMovieImage);

        holder.cardMovieInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent movieDetailsIntent = new Intent(mContext, MovieDetailsActivity.class);
                movieDetailsIntent.putExtra("MovieTitle",movieObject.getMovieName());
                movieDetailsIntent.putExtra("MovieId",movieObject.getMovieId());
                mContext.startActivity(movieDetailsIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieDataHolder extends RecyclerView.ViewHolder {

        TextView tvMovieName,tvReleaseDate,tvAdultFlag;
        ImageView ivMovieImage;
        CardView cardMovieInfo;

        public MovieDataHolder(View itemView) {
            super(itemView);
            cardMovieInfo = itemView.findViewById(R.id.cardMovieInfo);
            tvMovieName = itemView.findViewById(R.id.tvMovieName);
            tvReleaseDate = itemView.findViewById(R.id.tvReleaseDate);
            tvAdultFlag = itemView.findViewById(R.id.tvAdultFlag);

            ivMovieImage = itemView.findViewById(R.id.ivMovieImage);
        }
    }
}
