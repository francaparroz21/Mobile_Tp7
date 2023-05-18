package com.example.mobile_tp6.di

import androidx.room.Room
import com.example.mobile_tp6.data.database.MoviesRoomDatabase
import org.koin.dsl.module

object DBModule {
    private const val db = "MovieDatabase"

    val module = module {
        single { Room.databaseBuilder(get(), MoviesRoomDatabase::class.java, db).build() }
        single { get<MoviesRoomDatabase>().movieDao() }
    }
}