package com.example.popularmovies.di

import com.example.popularmovies.data.sources.MoviesLocalSource
import com.example.popularmovies.data.sources.MoviesRemoteSource
import com.example.popularmovies.data.sources.local.MoviesLocalSourceImpl
import com.example.popularmovies.data.sources.remote.MoviesRemoteSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
   abstract fun bindMovieRemoteSource (moviesRemoteSource: MoviesRemoteSourceImp) : MoviesRemoteSource

    @Binds
    abstract fun bindMoviesLocalSource(moviesLocalSource: MoviesLocalSourceImpl): MoviesLocalSource

}