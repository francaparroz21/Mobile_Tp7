package com.example.mobile_tp6.data.service

import com.example.mobile_tp6.domain.service.MovieService
import com.example.mobile_tp6.domain.entity.Movie
import com.example.mobile_tp6.util.CoroutineResult

class MovieServiceImplementation(private val client: MovieClient): MovieService{
    override suspend fun getMovies(): CoroutineResult<List<Movie>> {
        try {
            val response = client.getData().execute()
            if (response.isSuccessful){
                response.body()?.let { return CoroutineResult.Success(it) }
            }
            return CoroutineResult.Failure(Exception(response.errorBody().toString()))
        }catch (e:Exception){
            return CoroutineResult.Failure(e)
        }
    }
}