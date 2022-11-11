package com.example.popularmovies.common.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.popularmovies.data.sources.local.dao.MoviesDao
import com.example.popularmovies.data.sources.local.model.MovieEntity

@Database(
    version = 1,
    exportSchema = false,
    entities = [MovieEntity::class]
)
@TypeConverters()
abstract class AppDatabase : RoomDatabase() {

    abstract fun getMoviesDao(): MoviesDao

    companion object {
        const val DATA_BASE_NAME = "movies_database"
    }

}