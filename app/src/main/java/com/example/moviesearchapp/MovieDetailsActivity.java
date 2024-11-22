// MovieDetailsActivity.java
package com.example.moviesearchapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.moviesearchapp.databinding.ActivityMovieDetailsBinding;
import com.example.moviesearchapp.model.MovieDetails;
import com.example.moviesearchapp.viewmodel.MovieDetailsViewModel;

public class MovieDetailsActivity extends AppCompatActivity {

    private ActivityMovieDetailsBinding binding;
    private MovieDetailsViewModel movieDetailsViewModel;
    private String imdbID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize ViewModel
        movieDetailsViewModel = new ViewModelProvider(this).get(MovieDetailsViewModel.class);

        // Get IMDb ID from Intent
        imdbID = getIntent().getStringExtra("imdbID");

        // Fetch Movie Details
        if (imdbID != null) {
            getMovieDetails(imdbID);
        } else {
            Toast.makeText(this, "Movie ID not found", Toast.LENGTH_SHORT).show();
        }

        // Set Back Button Listener
        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void getMovieDetails(String imdbID) {
        movieDetailsViewModel.getMovieDetails(imdbID).observe(this, new Observer<MovieDetails>() {
            @Override
            public void onChanged(MovieDetails movieDetails) {
                if (movieDetails != null) {
                    // Update UI with movie details
                    binding.textViewTitle.setText(movieDetails.getTitle());
                    binding.textViewYear.setText("Year: " + movieDetails.getYear());
                    binding.textViewRated.setText("Rated: " + movieDetails.getRated());
                    binding.textViewRuntime.setText("Runtime: " + movieDetails.getRuntime());
                    binding.textViewGenre.setText("Genre: " + movieDetails.getGenre());
                    binding.textViewDirector.setText("Director: " + movieDetails.getDirector());
                    binding.textViewActors.setText("Actors: " + movieDetails.getActors());
                    binding.textViewPlot.setText("Plot: " + movieDetails.getPlot());

                    // Load Poster Image
                    Glide.with(MovieDetailsActivity.this)
                            .load(movieDetails.getPoster())
                            .placeholder(R.drawable.ic_launcher_background) // Placeholder image
                            .into(binding.imageViewPoster);
                } else {
                    Toast.makeText(MovieDetailsActivity.this, "Failed to load movie details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
