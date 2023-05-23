package com.example.mobile_tp6.di

import com.example.mobile_tp6.domain.usecase.GetMoviesUseCase
import com.example.mobile_tp6.domain.usecase.GetMoviesUseCaseImplementation
import org.koin.dsl.module

object UseCaseModule {
    val useCaseModule = module {
        factory<GetMoviesUseCase> { GetMoviesUseCaseImplementation(get(), get()) }
    }
}