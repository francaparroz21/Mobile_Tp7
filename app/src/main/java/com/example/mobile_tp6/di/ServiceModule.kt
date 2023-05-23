package com.example.mobile_tp6.di

import com.example.mobile_tp6.data.service.MovieServiceImplementation
import com.example.mobile_tp6.domain.service.MovieService
import org.koin.dsl.module

object ServiceModule {
    val serviceModule = module {
        factory <MovieService>{MovieServiceImplementation(get())}
    }
}