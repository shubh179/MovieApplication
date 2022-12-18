package com.example.movieapplication.di

import com.example.movieapplication.MainActivity
import com.example.movieapplication.di.module.ApiModule
import com.example.movieapplication.di.module.RepositoryModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, RepositoryModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}