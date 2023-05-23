package com.example.mobile_tp6.di

import com.example.mobile_tp6.domain.database.MovieDatabase
import com.example.mobile_tp6.data.database.MovieDatabaseImplementation
import org.koin.dsl.module

object DatabaseModule {
    val databaseModule = module {
        factory<MovieDatabase> { MovieDatabaseImplementation(get()) }
    }
}