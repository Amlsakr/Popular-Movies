package com.example.popularmovies.di

import com.example.popularmovies.data.MoviesRepositoryImp
import com.example.popularmovies.domain.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {

    @Binds
    abstract fun bindsMoviesRepository(moviesRepository: MoviesRepositoryImp):MoviesRepository
}