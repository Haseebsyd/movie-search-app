// SearchResponse.java
package com.example.moviesearchapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse {

    @SerializedName("Search")
    private List<MovieItem> searchResults;

    @SerializedName("totalResults")
    private String totalResults;

    @SerializedName("Response")
    private String response;

    // Getters
    public List<MovieItem> getSearchResults() {
        return searchResults;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public String getResponse() {
        return response;
    }
}
