package com.example.mobile_tp6.data.service

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRequestGenerator {
    private val API_MOVIES_URL = "https://api.themoviedb.org/3/"

    private val httpClient = OkHttpClient.Builder()

    private val builder = Retrofit.Builder()
        .baseUrl(API_MOVIES_URL)
        .addConverterFactory(GsonConverterFactory.create())


    fun <S> createService(serviceClass: Class<S>): S {
        try {
            val retrofit = builder.client(httpClient.build()).build()
            return retrofit.create(serviceClass)
        }catch (e:Exception){
            throw RuntimeException("Fail in Retrofit service.")
        }
    }

}