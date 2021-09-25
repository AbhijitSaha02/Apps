package com.example.myapplication

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieTopRated(
    @SerializedName("page")
    @Expose
    var page: Int? = null,
    @SerializedName("results")
    @Expose
    var results: List<MovieTopRatedResults>? = null,
    @SerializedName("total_results")
    @Expose
    var totalResults: Int? = null,
    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null
) {

    data class MovieTopRatedResults(
        @SerializedName("poster_path")
        @Expose
        var image: String? = null,
        @SerializedName("adult")
        @Expose
        var adult: Boolean? = null,
        @SerializedName("overview")
        @Expose
        var description: String? = null,
        @SerializedName("release_date")
        @Expose
        var releaseDate: String? = null,
        @SerializedName("genre_ids")
        @Expose
        var genre: List<Int>? = null,
        @SerializedName("id")
        @Expose
        var id: Int? = null,
        @SerializedName("original_title")
        @Expose
        var originalTitle: String? = null,
        @SerializedName("original_language")
        @Expose
        var originalLanguage: String? = null,
        @SerializedName("title")
        @Expose
        var title: String? = null,
        @SerializedName("backdrop_path")
        @Expose
        var backdropPath: String? = null,
        @SerializedName("popularity")
        @Expose
        var popularity: Double? = null,
        @SerializedName("vote_count")
        @Expose
        var voteCount: Int? = null,
        @SerializedName("video")
        @Expose
        var video: Boolean? = null,
        @SerializedName("vote_average")
        @Expose
        var rating: Float? = null
    )
}