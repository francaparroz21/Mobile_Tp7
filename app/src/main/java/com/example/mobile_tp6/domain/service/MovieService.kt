package com.example.mobile_tp6.domain.service
import com.example.mobile_tp6.domain.entity.Movie
import com.example.mobile_tp6.domain.util.CoroutineResult

interface MovieService {
    suspend fun getMovies(): CoroutineResult<List<Movie>>
}