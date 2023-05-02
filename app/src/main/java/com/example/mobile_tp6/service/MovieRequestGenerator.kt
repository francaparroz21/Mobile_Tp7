package com.example.mobile_tp6.service

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieRequestGenerator {
    private const val API_MOVIES_URL = "https://api.themoviedb.org/3"
    private const val API_KEY = "c14f9e72fded7c99ea38539b590ef335"

    private val requestInterceptor = Interceptor { chain ->
        val url: HttpUrl = chain.request()
            .url().newBuilder().addQueryParameter("api_key", API_KEY).build()

        val req: Request = chain.request().newBuilder().url(url).build()

        return@Interceptor chain.proceed(req)
    }

    private val httpClient = OkHttpClient.Builder().addInterceptor(requestInterceptor)

    private val builder = Retrofit.Builder()
        .baseUrl(API_MOVIES_URL)
        .addConverterFactory(GsonConverterFactory.create())


    fun <S> createService(serviceClass: Class<S>): S {
        val retrofit = builder.client(httpClient.build()).build()
        return retrofit.create(serviceClass)
    }

}