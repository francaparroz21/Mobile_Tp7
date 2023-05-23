package com.example.mobile_tp6.data.service.model
import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("page")
    var page: Int,
    @SerializedName("results")
    val results: MutableList<Movie>
)