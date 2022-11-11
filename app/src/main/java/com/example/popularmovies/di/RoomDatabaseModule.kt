package com.example.popularmovies.di

import android.content.Context
import androidx.room.Room
import com.example.popularmovies.common.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomDatabaseModule {

    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext appContext: Context): AppDatabase =
        Room.databaseBuilder(
            appContext.applicationContext,
            AppDatabase::class.java,
            AppDatabase.DATA_BASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
}