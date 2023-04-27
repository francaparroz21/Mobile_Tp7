package com.example.mobile_tp6.service
import android.telecom.Call
import com.example.mobile_tp6.service.model.MovieList
import retrofit2.http.GET

interface MovieClient {
    @GET("/movies/get-popular-movies")
    fun getData(): Call<MovieList>
}