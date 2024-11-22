// SearchViewModel.java
package com.example.moviesearchapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviesearchapp.model.MovieItem;
import com.example.moviesearchapp.repository.MovieRepository;

import java.util.List;

public class SearchViewModel extends ViewModel {

    private MovieRepository movieRepository;

    public SearchViewModel() {
        movieRepository = MovieRepository.getInstance();
    }

    public LiveData<List<MovieItem>> searchMovies(String query) {
        return movieRepository.searchMovies(query);
    }
}
