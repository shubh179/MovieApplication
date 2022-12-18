package com.example.movieapplication.repository

import android.util.Log
import com.example.movieapplication.ApiService
import com.example.movieapplication.data.GetMoviesResponse
import com.example.movieapplication.utils.ApiStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val apiService: ApiService) : MovieRepository {

    override suspend fun getMoviesList(page: Int): Flow<ApiStatus<GetMoviesResponse>> {
        return flow {

            // get the comment Data from the api
            val comment = apiService.getMovies(page = page)

            // Emit this data wrapped in
            // the helper class [CommentApiState]
            emit(ApiStatus.success(comment))
        }.flowOn(Dispatchers.IO)
    }

    override fun getPopularMovies(
        page: Int
    ) {
        apiService.getPopularMovies(page = page)
            .enqueue(object : Callback<GetMoviesResponse> {
                override fun onResponse(
                    call: Call<GetMoviesResponse>,
                    response: Response<GetMoviesResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            Log.d("Repository", "Movies: ${responseBody.movies}")
                        } else {
                            Log.d("Repository", "Failed to get response")
                        }
                    }
                }

                override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                    Log.e("Repository", "onFailure", t)
                }
            })
    }
}