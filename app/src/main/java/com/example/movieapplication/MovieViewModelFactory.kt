package com.example.movieapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapplication.repository.MoviesRepositoryImpl
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class MovieViewModelFactory @Inject constructor(private val moviesRepository: MoviesRepositoryImpl) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(moviesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}