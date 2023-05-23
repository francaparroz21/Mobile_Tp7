package com.example.mobile_tp6.domain.usecase

import com.example.mobile_tp6.domain.database.MovieDatabase
import com.example.mobile_tp6.domain.entity.Movie
import com.example.mobile_tp6.domain.service.MovieService
import com.example.mobile_tp6.util.CoroutineResult

interface GetMoviesUseCase {
    suspend operator fun invoke(): CoroutineResult<List<Movie>>
}

class GetMoviesUseCaseImplementation(
    private val service: MovieService,
    private val database: MovieDatabase
) : GetMoviesUseCase {
    override suspend operator fun invoke(): CoroutineResult<List<Movie>> {
        return when (val movies = service.getMovies()) {
            is CoroutineResult.Success -> {
                database.insertMovies(movies.data)
                database.getAllMovies()
            }
            is CoroutineResult.Failure -> {
                database.getAllMovies()
            }
        }
    }

}