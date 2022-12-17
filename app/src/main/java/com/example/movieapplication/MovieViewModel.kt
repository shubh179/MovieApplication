package com.example.movieapplication

import androidx.lifecycle.ViewModel
import com.example.movieapplication.repository.MoviesRepository

class MovieViewModel(moviesRepository: MoviesRepository) : ViewModel() {

    init {
        moviesRepository.getPopularMovies()
    }
}