package com.example.mobile_tp6.presentation.mvvm.model

import com.example.mobile_tp6.domain.entity.Movie
import com.example.mobile_tp6.domain.usecase.GetMoviesUseCase
import com.example.mobile_tp6.domain.util.CoroutineResult

class MovieModel(
    private val getMoviesUseCase: GetMoviesUseCase
) {
    suspend fun getMovies(): CoroutineResult<List<Movie>> = getMoviesUseCase()
}