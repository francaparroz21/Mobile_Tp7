package com.example.mobile_tp6.data.database


import com.example.mobile_tp6.data.entity.MovieEntity
import com.example.mobile_tp6.data.service.model.MovieList
import com.example.mobile_tp6.domain.entity.Movie

fun MovieList.transformToList():List<Movie>{
    val movieList = mutableListOf<Movie>()
    results.forEach {
        movieList.add(
            Movie(
                it.id,
                it.title,
                it.poster_path,
                it.release_date,
            )
        )
    }
    return movieList
}

fun MovieEntity.toMovie() = Movie(
    this.id,
    this.title,
    this.poster_path,
    this.release_date,
)

fun Movie.toMovieDB() = MovieEntity(
    this.id,
    this.title,
    this.poster_path,
    this.release_date,
)

fun List<MovieEntity>.toMovieList() = this.map { it.toMovie() }