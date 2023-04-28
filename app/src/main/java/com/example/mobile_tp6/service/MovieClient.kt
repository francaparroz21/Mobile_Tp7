package com.example.mobile_tp6.service
import retrofit2.Call
import com.example.mobile_tp6.service.model.MovieList
import retrofit2.http.GET

interface MovieClient {

    @GET("/movies/popular?api_key=c14f9e72fded7c99ea38539b590ef335")
    fun getData(): Call<MovieList>
}