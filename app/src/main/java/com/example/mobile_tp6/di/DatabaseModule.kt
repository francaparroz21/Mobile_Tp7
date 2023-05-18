package com.example.mobile_tp6.di
import com.example.mobile_tp6.data.database.MovieDatabase
import com.example.mobile_tp6.data.database.MovieDatabaseImplementation
import org.koin.dsl.module

object DatabaseModule {
    val module = module {
        factory<MovieDatabase> { MovieDatabaseImplementation(get()) }
    }
}