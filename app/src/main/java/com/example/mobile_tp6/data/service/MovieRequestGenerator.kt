package com.example.mobile_tp6.data.service

import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieRequestGenerator {
    private val API_MOVIES_URL = "https://api.themoviedb.org/3/"
    private const val API_KEY = "c14f9e72fded7c99ea38539b590ef335"

    private val httpClient = OkHttpClient.Builder()

    private val builder = Retrofit.Builder()
        .baseUrl(API_MOVIES_URL)
        .addConverterFactory(GsonConverterFactory.create())

    fun <S> createService(serviceClass: Class<S>): S {
        try {

            val retrofit = builder.client(httpClient.build())
            val interceptor = Interceptor { chain ->
                val req = chain.request()
                val url = req.url()
                val httpUrl = url.newBuilder()
                    .addQueryParameter("api_key", API_KEY)
                    .build()
                val requestBuilder = req.newBuilder().url(httpUrl)
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            val client = httpClient.addInterceptor(interceptor).build()
            return retrofit.client(client).build().create(serviceClass)
        } catch (e: Exception) {
            throw RuntimeException("Fail in Retrofit service.")
        }
    }

}