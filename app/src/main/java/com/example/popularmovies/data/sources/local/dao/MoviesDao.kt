package com.example.popularmovies.data.sources.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.popularmovies.data.sources.local.model.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class MoviesDao {

    @Query("SELECT * FROM Movies")
   abstract  fun getMovieList(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   abstract suspend fun updateMovieList(list:List<MovieEntity>)

    @Query("DELETE FROM Movies")
   abstract suspend fun deleteMovies()
}