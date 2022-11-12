package com.example.popularmovies.data.sources.local

import com.example.popularmovies.common.utill.SessionManager
import com.example.popularmovies.data.sources.MoviesLocalSource
import com.example.popularmovies.data.sources.local.dao.MoviesDao
import com.example.popularmovies.data.sources.local.model.MovieEntity
import com.example.popularmovies.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MoviesLocalSourceImpl @Inject constructor(
    private val moviesDao: MoviesDao ,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,


): MoviesLocalSource {
    override fun getMoviesList(): Flow<List<MovieEntity>> =
        flow{ emitAll(moviesDao.getMovieList())
    }.flowOn(ioDispatcher)

    override suspend fun updateMoviesList(list: List<MovieEntity>) {

        moviesDao.updateMovieList(list)
    }

    override suspend fun deleteMovie() {
        moviesDao.deleteMovies()
    }
}