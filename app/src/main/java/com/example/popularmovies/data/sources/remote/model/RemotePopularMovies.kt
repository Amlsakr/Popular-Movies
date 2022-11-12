package com.example.popularmovies.data.sources.remote.model


import com.google.gson.annotations.SerializedName

import androidx.annotation.Keep

@Keep
data class RemotePopularMovies(
    @SerializedName("page")
    var page: Int? = null, // 1
    @SerializedName("results")
    var results: List<RemoteMovie>? = null,
    @SerializedName("total_pages")
    var totalPages: Int? = null, // 35820
    @SerializedName("total_results")
    var totalResults: Int? = null // 716390
)

@Keep
data class RemoteMovie(
        @SerializedName("adult")
        var adult: Boolean? = null, // false
        @SerializedName("backdrop_path")
        var backdropPath: String? = null, // /y5Z0WesTjvn59jP6yo459eUsbli.jpg
        @SerializedName("genre_ids")
        var genreIds: List<Int?>? = null,
        @SerializedName("id")
        var id: String? = null, // 663712
        @SerializedName("original_language")
        var originalLanguage: String? = null, // en
        @SerializedName("original_title")
        var originalTitle: String? = null, // Terrifier 2
        @SerializedName("overview")
        var overview: String? = null, // After being resurrected by a sinister entity, Art the Clown returns to Miles County where he must hunt down and destroy a teenage girl and her younger brother on Halloween night.  As the body count rises, the siblings fight to stay alive while uncovering the true nature of Art's evil intent.
        @SerializedName("popularity")
        var popularity: Double? = null, // 6116.349
        @SerializedName("poster_path")
        var posterPath: String? = null, // /b6IRp6Pl2Fsq37r9jFhGoLtaqHm.jpg
        @SerializedName("release_date")
        var releaseDate: String? = null, // 2022-10-06
        @SerializedName("title")
        var title: String? = null, // Terrifier 2
        @SerializedName("video")
        var video: Boolean? = null, // false
        @SerializedName("vote_average")
        var voteAverage: String? = null, // 7.1
        @SerializedName("vote_count")
        var voteCount: Int? = null // 466
    )
