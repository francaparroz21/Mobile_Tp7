package com.example.mobile_tp6.service

import com.example.mobile_tp6.service.model.MovieList
import com.example.mobile_tp6.util.CoroutineResult

interface MovieService {
    suspend fun getMovies(): CoroutineResult<MovieList>

}

class MovieServiceImplementation(private val client: MovieClient): MovieService{
    override suspend fun getMovies(): CoroutineResult<MovieList> {
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