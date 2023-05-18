package com.example.mobile_tp6.data.service.model
import com.google.gson.annotations.SerializedName

data class Movie (
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("poster_path")
    var poster_path: String,
    @SerializedName("release_date")
    var release_date: String
    )