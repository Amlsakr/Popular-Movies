package com.example.popularmovies.di

import com.example.popularmovies.data.sources.remote.MoviesApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiServicesModule {

    @Provides
    @Singleton
    fun provideMoviesApiService(@MainClient retrofit: Retrofit):MoviesApiService{
        return retrofit.create(MoviesApiService::class.java)

    }
}