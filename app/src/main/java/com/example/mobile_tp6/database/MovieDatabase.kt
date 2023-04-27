package com.example.mobile_tp6.database

import com.example.mobile_tp6.database.dao.MovieDao
import com.example.mobile_tp6.service.model.Movie

interface MovieDatabase {
    suspend fun insertMovies(movies: List<Movie>)
    suspend fun getAllMovies(): List<Movie>
}

class MovieDatabaseImplementation(private val movieDao: MovieDao): MovieDatabase{
    override suspend fun insertMovies(movies: List<Movie>) {
        movies.forEach{
            movie -> movieDao.insertMovie(movies.mapToData)
        }
    }

    override suspend fun getAllMovies(): List<Movie> {
        TODO("Not yet implemented")
    }

}