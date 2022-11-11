package com.example.popularmovies.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    var backdropPath: String, // /y5Z0WesTjvn59jP6yo459eUsbli.jpg
    var id: String, // 663712
    var overview: String, // After being resurrected by a sinister entity, Art the Clown returns to Miles County where he must hunt down and destroy a teenage girl and her younger brother on Halloween night.  As the body count rises, the siblings fight to stay alive while uncovering the true nature of Art's evil intent.
    var posterPath: String, // /b6IRp6Pl2Fsq37r9jFhGoLtaqHm.jpg
    var releaseDate: String, // 2022-10-06
    var title: String, // Terrifier 2
    var voteAverage: String, // 7.1

):Parcelable

