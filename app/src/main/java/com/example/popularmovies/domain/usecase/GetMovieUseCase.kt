package com.example.popularmovies.domain.usecase

import com.example.popularmovies.domain.MoviesRepository
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val moviesRepository :MoviesRepository) {
    operator fun invoke(page:Int) = moviesRepository.getMoviesList(page)
}