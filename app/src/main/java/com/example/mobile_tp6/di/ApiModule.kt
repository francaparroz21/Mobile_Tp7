package com.example.mobile_tp6.di
import com.example.mobile_tp6.data.service.MovieRequestGenerator
import org.koin.dsl.module

object ApiModule {
    val apiModule = module {
        factory { MovieRequestGenerator }
    }
}