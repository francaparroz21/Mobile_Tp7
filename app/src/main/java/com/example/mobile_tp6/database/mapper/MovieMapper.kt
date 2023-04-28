package com.example.mobile_tp6.database.mapper

import com.example.mobile_tp6.database.entity.MovieEntity
import com.example.mobile_tp6.service.model.Movie

fun Movie.mapToDataBaseMovie(): MovieEntity = MovieEntity(
    id = id,
    title = title,
    poster_path = poster_path,
    release_date = release_date
)

fun List<MovieEntity>.mapToLocalMovie(): List<Movie> =
    map { entity ->
        Movie(
            id = entity.id,
            title = entity.title,
            poster_path = entity.poster_path,
            release_date = entity.release_date
        )
    }