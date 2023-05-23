package com.example.mobile_tp6.data.database
import com.example.mobile_tp6.data.database.dao.MovieDao
import com.example.mobile_tp6.domain.database.MovieDatabase
import com.example.mobile_tp6.util.CoroutineResult
import com.example.mobile_tp6.domain.entity.Movie
import java.lang.Exception

class MovieDatabaseImplementation(private val movieDao: MovieDao): MovieDatabase {
    override suspend fun insertMovies(movieList: List<Movie>) {
        movieList.forEach{
            movie -> movieDao.insertMovie(movie.toMovieDB())
        }
    }

    override suspend fun getAllMovies(): CoroutineResult<List<Movie>> {
        return movieDao.getDBMovies().let {
            if (it.isEmpty()) CoroutineResult.Failure(Exception())
            else CoroutineResult.Success(it.toMovieList())
        }
    }
}