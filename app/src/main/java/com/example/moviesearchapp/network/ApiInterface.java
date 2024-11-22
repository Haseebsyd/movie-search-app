// ApiInterface.java
package com.example.moviesearchapp.network;

import com.example.moviesearchapp.model.MovieDetails;
import com.example.moviesearchapp.model.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/")
    Call<SearchResponse> searchMovies(
            @Query("apikey") String apiKey,
            @Query("s") String query
    );

    @GET("/")
    Call<MovieDetails> getMovieDetails(
            @Query("apikey") String apiKey,
            @Query("i") String imdbID
    );
}
