package com.example.myapplication

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Dates(
    @SerializedName("maximum")
    @Expose
    var maximum: String? = null,
    @SerializedName("minimum")
    @Expose
    var minimum: String? = null
)