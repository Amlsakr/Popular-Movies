package com.example.popularmovies.data

import android.util.Log
import com.example.popularmovies.common.utill.Constant
import com.example.popularmovies.common.utill.SessionManager
import com.example.popularmovies.data.sources.MoviesLocalSource
import com.example.popularmovies.data.sources.MoviesRemoteSource
import com.example.popularmovies.data.sources.mapper.toMovieEntityList
import com.example.popularmovies.data.sources.mapper.toMovieList
import com.example.popularmovies.domain.MoviesRepository
import com.example.popularmovies.domain.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesRepositoryImp @Inject constructor(
    private val remoteSource: MoviesRemoteSource,
    private val localSource: MoviesLocalSource,
    var sessionManager: SessionManager
) : MoviesRepository {
    val coroutineScope = CoroutineScope(
        SupervisorJob() +
                Dispatchers.IO
    )
    override  fun getMoviesList(page: Int): Flow<List<Movie>> {
        if (sessionManager.availableToMakeForceUpdate()) {

            coroutineScope.launch {
                localSource.deleteMovie()
                loadMovies(1)
            }
        }
        return localSource.getMoviesList().map {
            it.toMovieList()
        }.catch {
                t -> Log.e("modelrepo" ,t.message.toString())
        }
    }

    private suspend fun loadMovies(page:Int){
        remoteSource.getMoviesList(page)
            .onEach {
                localSource.updateMoviesList(it!!.toMovieEntityList() )
                if(it.isNotEmpty() ){
                    loadMovies(page + 1)
                }
            }.catch {
                t -> Log.e("err" ,t.message.toString())
            }
            .flowOn(Dispatchers.IO)
            .collect()

    }

}

//        if (sessionManager.availableToMakeForceUpdate()) {
//            return remoteSource.getMoviesList(page)
//                .onEach {
//                    if (page == Constant.START_PAGE) {
//                        localSource.updateMoviesList(it.toMovieEntityList())
//                    }
//                }
//                .map {
//                    it.toMovieList()
//                }.catch { t ->
//                    Log.e("error", t.message.toString())
//                    if (page == Constant.START_PAGE) {
//                        emitAll(localSource.getMoviesList().map { it.toMovieList() })
//                    } else {
//                        emit(emptyList())
//                    }
//                }
//
//        } else {
//return localSource.getMoviesList()
//    .map {
//        it.toMovieList()
//    }
//        }