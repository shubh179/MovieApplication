package com.example.movieapplication.di.module

import com.example.movieapplication.ApiService
import com.example.movieapplication.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesMoviesRepository(apiService: ApiService): MoviesRepository {
        return MoviesRepository(apiService)
    }
}