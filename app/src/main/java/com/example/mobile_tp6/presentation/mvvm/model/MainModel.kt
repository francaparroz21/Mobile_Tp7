package com.example.mobile_tp6.presentation.mvvm.model

import com.example.mobile_tp6.data.database.MovieDatabase
import com.example.mobile_tp6.presentation.mvvm.contract.MainContract
import com.example.mobile_tp6.data.service.MovieService
import com.example.mobile_tp6.data.service.model.Movie
import com.example.mobile_tp6.util.CoroutineResult
import java.lang.Exception

class MainModel(
    private val service: MovieService,
    private val database: MovieDatabase
) : MainContract.Model {
    override suspend fun getPopularMovies(): CoroutineResult<List<Movie>> {
        return when (val movies = service.getMovies()) {
            is CoroutineResult.Success -> {
                database.insertMovies(movies.data.results)
                CoroutineResult.Success(database.getAllMovies())
            }
            is CoroutineResult.Failure -> {
                CoroutineResult.Success(database.getAllMovies())
                /*
                Show error fragment:
                CoroutineResult.Failure(Exception())
                 */
            }
        }
    }
}