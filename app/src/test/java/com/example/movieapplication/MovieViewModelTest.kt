package com.example.movieapplication

import com.example.movieapplication.repository.MovieRepository
import com.example.movieapplication.utils.Status
import com.example.movieapplication.viewModel.MovieViewModel
import kotlinx.coroutines.*
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.anyInt
import org.mockito.kotlin.*

class MovieViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val movieRepository = mock<MovieRepository>()

    private lateinit var viewModel: MovieViewModel

    @Test
    fun testLoadingState() = runBlocking {
        whenever(movieRepository.getMoviesList(anyInt())).doSuspendableAnswer {
            withContext(Dispatchers.IO) { delay(5000) }
            null
        }

        viewModel = MovieViewModel(movieRepository)
        Assert.assertEquals(Status.LOADING, viewModel.movieListState.value.status)
    }
}