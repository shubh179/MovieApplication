package com.example.movieapplication.di.module

import com.example.movieapplication.ApiService
import com.example.movieapplication.Constants.BASE_URL
import com.example.movieapplication.MainActivity
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@Module
class ApiModule {

    @Provides
    fun provideRetrofitInterface() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }
}