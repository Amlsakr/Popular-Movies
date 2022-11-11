package com.example.popularmovies.data.sources.remote


import com.example.popularmovies.BuildConfig.API_KEY
import com.example.popularmovies.data.sources.remote.model.RemoteMovie
import com.example.popularmovies.data.sources.remote.model.RemotePopularMovies

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiService {

    @GET("popular")
    fun getPopularMovies(
        @Query("api_key") apiKey :String = API_KEY,
        @Query("language") language :String = "en",
        @Query("page") page :Int =1,

        ): RemotePopularMovies
}