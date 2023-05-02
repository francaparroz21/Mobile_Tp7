package com.example.mobile_tp6.service

import retrofit2.Call
import com.example.mobile_tp6.service.model.MovieList
import retrofit2.http.GET
import retrofit2.http.Headers

interface MovieClient {

    @Headers("api_key: " + "c14f9e72fded7c99ea38539b590ef335")
    @GET("/movie/popular")
    fun getData(): Call<MovieList>
}