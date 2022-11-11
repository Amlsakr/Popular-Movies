package com.example.popularmovies.data.sources.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movies")
data class MovieEntity(
    var backdropPath: String,
    @PrimaryKey
    var id: String,
    var overview: String,
    var posterPath: String,
    var releaseDate: String,
    var title: String,
    var voteAverage: String,
)
