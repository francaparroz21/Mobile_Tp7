package com.example.mobile_tp6.di
import com.example.mobile_tp6.data.service.MovieService
import com.example.mobile_tp6.data.service.MovieServiceImplementation
import org.koin.dsl.module

object ServiceModule {
    val module = module {
        factory<MovieService> { MovieServiceImplementation(get()) }
    }
}