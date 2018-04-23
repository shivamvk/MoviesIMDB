package com.example.shivamvk.moviesimdb;

public class Constants {

    public static final String TRENDING_MOVIES_REQUEST_URL =
            "https://api.themoviedb.org/3/discover/movie?api_key=8eb58870b5de5cb9bc488acbde9db02f&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1";
    public static final String IMAGES_URL =
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2";
    public static final String ACTION_MOVIES_REQUEST_URL =
            "https://api.themoviedb.org/3/discover/movie?api_key=8eb58870b5de5cb9bc488acbde9db02f&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_genres=28";
    public static final String COMEDY_MOVIES_REQUEST_URL =
            "https://api.themoviedb.org/3/discover/movie?api_key=8eb58870b5de5cb9bc488acbde9db02f&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_genres=35";
    public static final String SEARCH_QUERY_REQUEST_URL =
            "https://api.themoviedb.org/3/search/movie?api_key=8eb58870b5de5cb9bc488acbde9db02f&language=hindi&page=1&include_adult=false&query=";
    public static final String TRENDING_TVSHOWS_REQUEST_URL =
            "https://api.themoviedb.org/3/discover/tv?api_key=8eb58870b5de5cb9bc488acbde9db02f&language=en-US&sort_by=popularity.desc&page=1&timezone=America%2FNew_York&include_null_first_air_dates=false";
    public static final String ACTION_TVSHOWS_REQUEST_URL =
            "https://api.themoviedb.org/3/discover/tv?api_key=8eb58870b5de5cb9bc488acbde9db02f&language=en-US&sort_by=popularity.desc&page=1&timezone=America%2FNew_York&with_genres=28&include_null_first_air_dates=false";
    public static final String COMEDY_TVSHOWS_REQUEST_URL =
            "https://api.themoviedb.org/3/discover/tv?api_key=8eb58870b5de5cb9bc488acbde9db02f&language=en-US&sort_by=popularity.desc&page=1&timezone=America%2FNew_York&with_genres=35&include_null_first_air_dates=false";

}
