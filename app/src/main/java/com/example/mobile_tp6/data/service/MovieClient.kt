package com.example.mobile_tp6.data.service
import com.example.mobile_tp6.data.service.model.MovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "c14f9e72fded7c99ea38539b590ef335"
interface MovieClient {
    @GET("movie/popular")
    fun getData(): Call<MovieList>
}