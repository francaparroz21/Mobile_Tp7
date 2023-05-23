package com.example.mobile_tp6.data.service

import com.example.mobile_tp6.data.database.transformToList
import com.example.mobile_tp6.domain.service.MovieService
import com.example.mobile_tp6.domain.entity.Movie
import com.example.mobile_tp6.util.CoroutineResult

class MovieServiceImplementation(private val requestGenerator: MovieRequestGenerator): MovieService{
    override suspend fun getMovies(): CoroutineResult<List<Movie>> {
        try {
            val callResponse = requestGenerator.createService(MovieClient::class.java).getData()
            val response = callResponse.execute()
            if (response.isSuccessful){
                response.body()?.let { return CoroutineResult.Success(it.transformToList()) }
            }
            return CoroutineResult.Failure(Exception(response.errorBody().toString()))
        }catch (e:Exception){
            return CoroutineResult.Failure(e)
        }
    }
}