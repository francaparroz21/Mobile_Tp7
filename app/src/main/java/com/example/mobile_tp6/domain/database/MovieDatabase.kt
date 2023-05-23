package com.example.mobile_tp6.domain.database
import com.example.mobile_tp6.domain.entity.Movie
import com.example.mobile_tp6.domain.util.CoroutineResult

interface MovieDatabase {
    suspend fun insertMovies(movieList: List<Movie>)
    suspend fun getAllMovies(): CoroutineResult<List<Movie>>
}