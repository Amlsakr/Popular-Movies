package com.example.popularmovies.data.sources.mapper

import com.example.popularmovies.data.sources.local.model.MovieEntity
import com.example.popularmovies.data.sources.remote.model.RemoteMovie
import com.example.popularmovies.domain.model.Movie

fun RemoteMovie.toMovie() = Movie(
    backdropPath = backdropPath.orEmpty(),
    id = id.orEmpty(),
    overview = overview.orEmpty(),
    posterPath = posterPath.orEmpty(),
    releaseDate = releaseDate.orEmpty(),
    title = title.orEmpty(),
    voteAverage = voteAverage.orEmpty()
)

@JvmName("mapRemoteListToMovieList")
fun List<RemoteMovie>.toMovieList() = map { it.toMovie() }

fun MovieEntity.toMovie(): Movie = Movie(
    backdropPath = backdropPath.orEmpty(),
    id = id,
    overview = overview.orEmpty(),
    posterPath = posterPath.orEmpty(),
    releaseDate = releaseDate.orEmpty(),
    title = title.orEmpty(),
    voteAverage = voteAverage
)

@JvmName("mapEntityListToMovieList")
fun List<MovieEntity>.toMovieList() = map { it.toMovie() }
fun RemoteMovie.toMovieEntity() = MovieEntity(
    backdropPath = backdropPath.orEmpty(),
    id = id.orEmpty(),
    overview = overview.orEmpty(),
    posterPath = posterPath.orEmpty(),
    releaseDate = releaseDate.orEmpty(),
    title = title.orEmpty(),
    voteAverage = voteAverage.orEmpty()
)

fun List<RemoteMovie>.toMovieEntityList() = map { it.toMovieEntity() }