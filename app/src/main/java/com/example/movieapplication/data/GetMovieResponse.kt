package com.example.movieapplication.data

import com.google.gson.annotations.SerializedName

data class GetMoviesResponse(
    @SerializedName("page") val page: Int ?= null,
    @SerializedName("results") val movies: List<Movie> ?= null,
    @SerializedName("total_pages") val pages: Int ?= null
)
