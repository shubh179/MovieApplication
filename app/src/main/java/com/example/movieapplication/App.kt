package com.example.movieapplication

import android.app.Application
import com.example.movieapplication.di.AppComponent
import com.example.movieapplication.di.DaggerAppComponent

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .build()
    }
}

lateinit var appComponent: AppComponent