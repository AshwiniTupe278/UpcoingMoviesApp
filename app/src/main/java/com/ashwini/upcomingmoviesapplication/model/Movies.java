package com.ashwini.upcomingmoviesapplication.model;

/**
 * Created by softdotcom-7 on 26/12/17.
 */

public class Movies {
    private String movieName,movieReleaseDate,movieAdult,movieImageURL;

    public Movies(String movieName, String movieReleaseDate, String movieAdult, String movieImageURL) {
        this.movieName = movieName;
        this.movieReleaseDate = movieReleaseDate;
        this.movieAdult = movieAdult;
        this.movieImageURL = movieImageURL;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(String movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public String getMovieAdult() {
        return movieAdult;
    }

    public void setMovieAdult(String movieAdult) {
        this.movieAdult = movieAdult;
    }

    public String getMovieImageURL() {
        return movieImageURL;
    }

    public void setMovieImageURL(String movieImageURL) {
        this.movieImageURL = movieImageURL;
    }


}
