package com.example.mobile_tp6.mvvm.model

import com.example.mobile_tp6.mvvm.contract.MainContract
import com.example.mobile_tp6.service.MovieService
import com.example.mobile_tp6.service.model.Movie
import com.example.mobile_tp6.util.CoroutineResult

class MainModel(
    private val service: MovieService,
    private val database: MovieDatabase
): MainContract.Model {
    override suspend fun getPopularMovies(): CoroutineResult<List<Movie>> {
        return when ( val movies = service.getMovies()){
            is CoroutineResult.Success -> {
            }
        }
    }
}