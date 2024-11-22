// MainActivity.java
package com.example.moviesearchapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.moviesearchapp.adapter.MovieAdapter;
import com.example.moviesearchapp.databinding.ActivityMainBinding;
import com.example.moviesearchapp.model.MovieItem;
import com.example.moviesearchapp.viewmodel.SearchViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private SearchViewModel searchViewModel;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);

        initRecyclerView();

        binding.buttonSearch.setOnClickListener(v -> {
            String query = binding.editTextSearch.getText().toString().trim();
            if (!query.isEmpty()) {
                searchMovies(query);
            } else {
                Toast.makeText(MainActivity.this, "Please enter a search term", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initRecyclerView() {
        binding.recyclerViewMovies.setLayoutManager(new LinearLayoutManager(this));
        movieAdapter = new MovieAdapter(new ArrayList<>());
        binding.recyclerViewMovies.setAdapter(movieAdapter);

        movieAdapter.setOnItemClickListener(movie -> {
            // Navigate to Movie Details Screen
            Intent intent = new Intent(MainActivity.this, MovieDetailsActivity.class);
            intent.putExtra("imdbID", movie.getImdbID());
            startActivity(intent);
        });
    }

    private void searchMovies(String query) {
        searchViewModel.searchMovies(query).observe(this, new Observer<List<MovieItem>>() {
            @Override
            public void onChanged(List<MovieItem> movieItems) {
                if (movieItems != null && !movieItems.isEmpty()) {
                    movieAdapter.setMovieList(movieItems);
                } else {
                    Toast.makeText(MainActivity.this, "No results found", Toast.LENGTH_SHORT).show();
                    movieAdapter.setMovieList(new ArrayList<>());
                }
            }
        });
    }
}
