package com.example.popularmovies.data.sources.remote

import com.example.popularmovies.data.sources.MoviesRemoteSource
import com.example.popularmovies.data.sources.remote.model.RemoteMovie

import com.example.popularmovies.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MoviesRemoteSourceImp @Inject constructor(
    private val moviesApiService: MoviesApiService,
    @IoDispatcher private val ioDispatcher:CoroutineDispatcher
) :MoviesRemoteSource{
    override fun getMoviesList(page:Int): Flow<List<RemoteMovie>?> = flow {
        emit(moviesApiService.getPopularMovies(page = page).results)
    }.flowOn(ioDispatcher)
}