package com.example.popularmovies.data.sources

import com.example.popularmovies.data.sources.local.model.MovieEntity
import com.example.popularmovies.data.sources.remote.model.RemoteMovie

import kotlinx.coroutines.flow.Flow

interface MoviesLocalSource {

    fun getMoviesList():Flow<List<MovieEntity>>

    suspend fun updateMoviesList(list :List<MovieEntity>)

    suspend fun deleteMovie()
}
interface MoviesRemoteSource {

    fun getMoviesList(page:Int):Flow<List<RemoteMovie>?>
}