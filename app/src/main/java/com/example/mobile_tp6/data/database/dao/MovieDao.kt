package com.example.mobile_tp6.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mobile_tp6.data.database.entity.MovieEntity

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movieEntity: MovieEntity): Long

    @Query("SELECT * FROM movies")
    fun getDBMovies(): List<MovieEntity>
}