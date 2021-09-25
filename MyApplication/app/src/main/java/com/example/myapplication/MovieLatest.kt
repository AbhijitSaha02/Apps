package com.example.myapplication

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class MovieLatest(
    @SerializedName("adult")
    @Expose
    var adult: Boolean? = null,
    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null,
    @SerializedName("belongs_to_collection")
    @Expose
    var collection: KotlinNullPointerException? = null,
    @SerializedName("budget")
    @Expose
    var budget: Int? = null,
    @SerializedName("genres")
    @Expose
    var genres: List<LatestGenres>? = null,
    @SerializedName("homepage")
    @Expose
    var homepage: String? = null,
    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("imdb_id")
    @Expose
    var imdbId: String? = null,
    @SerializedName("original_language")
    @Expose
    var originalLanguage: String? = null,
    @SerializedName("original_title")
    @Expose
    var originalTitle: String? = null,
    @SerializedName("overview")
    @Expose
    var description: String? = null,
    @SerializedName("popularity")
    @Expose
    var popularity: Double? = null,
    @SerializedName("poster_path")
    @Expose
    var image: String? = null,
    @SerializedName("production_companies")
    @Expose
    var productionCompanies: List<Objects>? = null,
    @SerializedName("production_countries")
    @Expose
    var productionCountries: List<Objects>? = null,
    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null,
    @SerializedName("revenue")
    @Expose
    var revenue: Int? = null,
    @SerializedName("runtime")
    @Expose
    var runtime: Int? = null,
    @SerializedName("spoken_languages")
    @Expose
    var spokenLanguages: List<Objects>? = null,
    @SerializedName("status")
    @Expose
    var status: String? = null,
    @SerializedName("tagline")
    @Expose
    var tagline: String? = null,
    @SerializedName("title")
    @Expose
    var title: String? = null,
    @SerializedName("video")
    @Expose
    var video: Boolean? = null,
    @SerializedName("vote_average")
    @Expose
    var rating: Float? = null,
    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = null
) {

    data class LatestGenres(
        @SerializedName("id")
        @Expose
        var genreId: Int? = null,
        @SerializedName("name")
        @Expose
        var genreName: String? = null
    )
}