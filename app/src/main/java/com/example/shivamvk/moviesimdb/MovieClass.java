package com.example.shivamvk.moviesimdb;

public class MovieClass {

    private String stMovieRating;
    private String stMovieTitle;
    private String stMoviePosterPath;
    private String stMovieBackdropPath;
    private String stMovieOverview;
    private String stMovieReleaseDate;
    private String stMovieGenre;

    public MovieClass(){

    }

    public MovieClass(String stMovieRating, String stMovieTitle, String stMoviePosterPath, String stMovieBackdropPath, String stMovieOverview, String stMovieReleaseDate, String stMovieGenre) {
        this.stMovieRating = stMovieRating;
        this.stMovieTitle = stMovieTitle;
        this.stMoviePosterPath = stMoviePosterPath;
        this.stMovieBackdropPath = stMovieBackdropPath;
        this.stMovieOverview = stMovieOverview;
        this.stMovieReleaseDate = stMovieReleaseDate;
        this.stMovieGenre = stMovieGenre;
    }

    public String getStMovieRating() {
        return stMovieRating;
    }

    public String getStMovieTitle() {
        return stMovieTitle;
    }

    public String getStMoviePosterPath() {
        return stMoviePosterPath;
    }

    public String getStMovieBackdropPath() {
        return stMovieBackdropPath;
    }

    public String getStMovieOverview() {
        return stMovieOverview;
    }

    public String getStMovieReleaseDate() {
        return stMovieReleaseDate;
    }

    public String getStMovieGenre(){
        return stMovieGenre;
    }
}
