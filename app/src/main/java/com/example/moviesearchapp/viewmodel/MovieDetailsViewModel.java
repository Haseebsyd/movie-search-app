// MovieDetailsViewModel.java
package com.example.moviesearchapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviesearchapp.model.MovieDetails;
import com.example.moviesearchapp.repository.MovieRepository;


public class MovieDetailsViewModel extends ViewModel {

    private MovieRepository movieRepository;

    public MovieDetailsViewModel() {
        movieRepository = MovieRepository.getInstance();
    }

    public LiveData<MovieDetails> getMovieDetails(String imdbID) {
        return movieRepository.getMovieDetails(imdbID);
    }
}
