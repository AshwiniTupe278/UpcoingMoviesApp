package com.ashwini.upcomingmoviesapplication.model;

/**
 * Created by softdotcom-7 on 27/12/17.
 */

public class MovieModel {

    String movieName,movieReleaseDate;

    boolean movieAdultFlag;

    public MovieModel(String movieName, String movieReleaseDate, boolean movieAdultFlag) {
        this.movieName = movieName;
        this.movieReleaseDate = movieReleaseDate;
        this.movieAdultFlag = movieAdultFlag;
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

    public boolean isMovieAdultFlag() {
        return movieAdultFlag;
    }

    public void setMovieAdultFlag(boolean movieAdultFlag) {
        this.movieAdultFlag = movieAdultFlag;
    }
}
