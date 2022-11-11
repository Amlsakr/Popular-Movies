package com.example.popularmovies.data

import android.util.Log
import com.example.popularmovies.common.utill.Constant
import com.example.popularmovies.data.sources.MoviesLocalSource
import com.example.popularmovies.data.sources.MoviesRemoteSource
import com.example.popularmovies.data.sources.mapper.toMovieEntityList
import com.example.popularmovies.data.sources.mapper.toMovieList
import com.example.popularmovies.domain.MoviesRepository
import com.example.popularmovies.domain.model.Movie
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MoviesRepositoryImp @Inject constructor(
    private val remoteSource: MoviesRemoteSource,
    private val localSource: MoviesLocalSource
) : MoviesRepository {
    override fun getMoviesList(page: Int): Flow<List<Movie>> {
        return remoteSource.getMoviesList(page)
            .onEach {
                if (page == Constant.START_PAGE) {

                    localSource.updateMoviesList(it.toMovieEntityList())
                }
            }
            .map {
                it.toMovieList()
            }.catch { t ->
                Log.e("error", t.message.toString())
                if (page == Constant.START_PAGE) {
                    emitAll(localSource.getMoviesList().map { it.toMovieList() })
                } else {
                    emit(emptyList())
                }
            }

    }

}