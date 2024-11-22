// MovieRepository.java
package com.example.moviesearchapp.repository;

// Imports
import androidx.lifecycle.MutableLiveData;
import com.example.moviesearchapp.model.MovieDetails;
import com.example.moviesearchapp.model.MovieItem;
import com.example.moviesearchapp.model.SearchResponse;
import com.example.moviesearchapp.network.ApiClient;
import com.example.moviesearchapp.network.ApiInterface;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    // Singleton instance
    private static MovieRepository instance;
    // Declaration of apiInterface
    private ApiInterface apiInterface;

    // Constructor
    private MovieRepository() {
        // Initialization of apiInterface
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    // Singleton method to get the instance
    public static MovieRepository getInstance() {
        if (instance == null) {
            instance = new MovieRepository();
        }
        return instance;
    }

    // Method to search movies
    public MutableLiveData<List<MovieItem>> searchMovies(String query) {
        MutableLiveData<List<MovieItem>> data = new MutableLiveData<>();

        apiInterface.searchMovies("8ffadbe2", query).enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.body() != null && response.body().getSearchResults() != null) {
                    data.setValue(response.body().getSearchResults());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    // Method to get movie details
    public MutableLiveData<MovieDetails> getMovieDetails(String imdbID) {
        MutableLiveData<MovieDetails> data = new MutableLiveData<>();

        apiInterface.getMovieDetails("8ffadbe2", imdbID).enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}
