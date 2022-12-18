package com.example.movieapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapplication.data.GetMoviesResponse
import com.example.movieapplication.repository.MovieRepository
import com.example.movieapplication.utils.ApiStatus
import com.example.movieapplication.utils.Status
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val commentState = MutableStateFlow(
        ApiStatus(
            Status.LOADING,
            GetMoviesResponse(), ""
        )
    )

    init {
//        withContext(Dispatchers.IO) {
//            val movie = moviesRepository.getPopularMovies(1)
//            Log.d("ViewModel", "Movies: ${movie}")
//        }
        getMovieList(1)
    }

    // Function to get new Comments
    private fun getMovieList(page: Int) {

        // Since Network Calls takes time,Set the
        // initial value to loading state
        commentState.value = ApiStatus.loading()

        // ApiCalls takes some time, So it has to be
        // run and background thread. Using viewModelScope
        // to call the api
        viewModelScope.launch {

            // Collecting the data emitted
            // by the function in repository
            movieRepository.getMoviesList(1)
                // If any errors occurs like 404 not found
                // or invalid query, set the state to error
                // State to show some info
                // on screen
                .catch {
                    commentState.value =
                        ApiStatus.error(it.message.toString())
                }
                // If Api call is succeeded, set the State to Success
                // and set the response data to data received from api
                .collect {
                    commentState.value = ApiStatus.success(it.data)
                }
        }
    }
}