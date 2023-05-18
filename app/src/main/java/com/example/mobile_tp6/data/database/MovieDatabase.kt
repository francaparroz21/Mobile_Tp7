package com.example.mobile_tp6.data.database
import com.example.mobile_tp6.data.database.dao.MovieDao
import com.example.mobile_tp6.data.database.mapper.mapToDataBaseMovie
import com.example.mobile_tp6.data.database.mapper.mapToLocalMovie
import com.example.mobile_tp6.data.service.model.Movie

interface MovieDatabase {
    suspend fun insertMovies(movies: List<Movie>)
    suspend fun getAllMovies(): List<Movie>
}

class MovieDatabaseImplementation(private val movieDao: MovieDao): MovieDatabase {
    override suspend fun insertMovies(movies: List<Movie>) {
        movies.forEach{
            movie -> movieDao.insertMovie(movie.mapToDataBaseMovie())
        }
    }

    override suspend fun getAllMovies(): List<Movie> {
        return movieDao.getDBMovies().mapToLocalMovie()
    }

}