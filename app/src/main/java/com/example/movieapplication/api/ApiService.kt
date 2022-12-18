package com.example.movieapplication.api

import com.example.movieapplication.utils.Constants.API_KEY
import com.example.movieapplication.data.GetMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getMovies(
        @Query("api_key") api_key : String = API_KEY,
        @Query("page") page : Int
    ) : GetMoviesResponse
}