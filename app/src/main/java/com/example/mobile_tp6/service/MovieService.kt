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
            if (response.is)
        }catch (e:Exception){
            return CoroutineResult.Failure(e)
        }
    }

}