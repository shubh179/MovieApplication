package com.example.movieapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.example.movieapplication.adapter.MovieAdapter
import com.example.movieapplication.databinding.ActivityMainBinding
import com.example.movieapplication.utils.Status
import com.example.movieapplication.viewModel.MovieViewModel
import com.example.movieapplication.viewModel.MovieViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: MovieViewModelFactory

    private lateinit var viewModel: MovieViewModel
    private lateinit var binding: ActivityMainBinding
    lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProviders.of(this, factory)[MovieViewModel::class.java]

        lifecycleScope.launch {
            viewModel.movieListState.collect {
                Log.d("Activity: ", "status")
                when (it.status) {
                    Status.LOADING -> {
                        binding.progressBar.isVisible = true
                        Log.d("Activity: ", "null")
                    }
                    Status.SUCCESS -> {
                        binding.progressBar.isVisible = false
                        it.data?.let { movieList ->
                            movieAdapter = MovieAdapter(movieList.movies)
                            binding.movieRecyclerView.adapter = movieAdapter
                            Log.d("Activity: ", movieList.movies?.size.toString())
                        }
                    }
                    else -> {
                        binding.progressBar.isVisible = false
                        Log.d("Activity: ", it.message.toString())
                        Toast.makeText(this@MainActivity, "${it.message}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }
}