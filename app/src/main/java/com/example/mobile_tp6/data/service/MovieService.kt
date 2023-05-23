package com.example.mobile_tp6.data.service

import com.example.mobile_tp6.data.service.model.MovieList
import com.example.mobile_tp6.domain.util.CoroutineResult

interface MovieService {
    suspend fun getMovies(): CoroutineResult<MovieList>

}
