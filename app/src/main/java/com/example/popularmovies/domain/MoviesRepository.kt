package com.example.popularmovies.domain

import com.example.popularmovies.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getMoviesList(page:Int):    Flow<List<Movie>>
}