package com.example.movieapplication.repository

import com.example.movieapplication.api.ApiService
import com.example.movieapplication.data.GetMoviesResponse
import com.example.movieapplication.utils.ApiStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val apiService: ApiService) : MovieRepository {

    override suspend fun getMoviesList(page: Int): Flow<ApiStatus<GetMoviesResponse>> {
        return flow {
            val movieList = apiService.getMovies(page = page)
            emit(ApiStatus.success(movieList))
        }.flowOn(Dispatchers.IO)
    }
}