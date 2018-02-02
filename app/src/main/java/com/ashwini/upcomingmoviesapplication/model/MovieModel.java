package com.ashwini.upcomingmoviesapplication.model;


public class MovieModel {

    String movieName,movieReleaseDate,movieId,posterPath;

    boolean movieAdultFlag;

    public MovieModel(String movieName, String movieReleaseDate, boolean movieAdultFlag) {
        this.movieName = movieName;
        this.movieReleaseDate = movieReleaseDate;
        this.movieAdultFlag = movieAdultFlag;
    }

    public MovieModel() {

    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
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
