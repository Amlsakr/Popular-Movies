package com.example.popularmovies.di

import com.example.popularmovies.common.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DaoModule {

    @Provides
    @Singleton
    fun provideMoviesDao(database: AppDatabase) = database.getMoviesDao()
}