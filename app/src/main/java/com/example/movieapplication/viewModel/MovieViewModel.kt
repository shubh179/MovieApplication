package com.example.movieapplication.viewModel

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

    val movieListState = MutableStateFlow(
        ApiStatus(
            Status.LOADING,
            GetMoviesResponse(), ""
        )
    )

    init {
        getMovieList(1)
    }

    private fun getMovieList(page: Int) {
        movieListState.value = ApiStatus.loading()

        viewModelScope.launch {
            movieRepository.getMoviesList(1)
                .catch {
                    movieListState.value =
                        ApiStatus.error(it.message.toString())
                }
                .collect {
                    movieListState.value = ApiStatus.success(it.data)
                }
        }
    }
}