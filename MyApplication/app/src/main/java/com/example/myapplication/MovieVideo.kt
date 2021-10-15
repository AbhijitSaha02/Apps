package com.example.myapplication

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieVideo(
    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("results")
    @Expose
    var results : List<MovieVideoResults>? =null
) {
    data class MovieVideoResults(
        @SerializedName("iso_639_1")
        @Expose
        var iso_639_1 : String? = null,
        @SerializedName("iso_3166_1")
        @Expose
        var iso_3166_1 : String? = null,
        @SerializedName("name")
        @Expose
        var name : String? = null,
        @SerializedName("key")
        @Expose
        var key : String? = null,
        @SerializedName("site")
        @Expose
        var site : String? = null,
        @SerializedName("size")
        @Expose
        var size : Int? = null,
        @SerializedName("type")
        @Expose
        var type : String? = null,
        @SerializedName("official")
        @Expose
        var official : Boolean? = null,
        @SerializedName("published_at")
        @Expose
        var publish : String? = null,
        @SerializedName("id")
        @Expose
        var video_id : String? = null
    )
}