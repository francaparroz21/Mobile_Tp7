package com.example.mobile_tp6.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object MovieRequestGenerator {
    private const val API_MOVIES_URL = "https://api.themoviedb.org/3/"
    private val httpClient = OkHttpClient.Builder()

    private val builder = Retrofit.Builder()
        .baseUrl(API_MOVIES_URL)
        .addConverterFactory(GsonConverterFactory.create())


    fun <S> createService(serviceClass: Class<S>):S{
        val retrofit = builder.client(httpClient.build()).build()
        return retrofit.create(serviceClass)
    }

}