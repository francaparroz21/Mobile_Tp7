package com.example.mobile_tp6.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
class MovieEntity(
    @PrimaryKey var id: Int,
    var title: String,
    var poster_path: String,
    var release_date: String
)