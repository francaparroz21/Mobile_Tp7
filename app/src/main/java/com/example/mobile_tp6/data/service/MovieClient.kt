package com.example.mobile_tp6.data.service
import retrofit2.Call
import com.example.mobile_tp6.domain.entity.Movie
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "c14f9e72fded7c99ea38539b590ef335"
interface MovieClient {
    @GET("movie/popular")
    fun getData(@Query("api_key")apiKey: String = API_KEY): Call<List<Movie>>
}