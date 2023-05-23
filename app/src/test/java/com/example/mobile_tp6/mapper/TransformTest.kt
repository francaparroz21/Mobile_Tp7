package com.example.mobile_tp6.mapper

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mobile_tp6.data.database.toMovie
import com.example.mobile_tp6.data.database.toMovieDB
import com.example.mobile_tp6.data.database.toMovieList
import com.example.mobile_tp6.data.entity.MovieEntity
import com.example.mobile_tp6.domain.entity.Movie
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class TransformTest {
    private lateinit var movie: Movie
    private lateinit var entity: MovieEntity
    private lateinit var entityList: List<MovieEntity>

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup(){
        movie = Movie(ID, TITLE, POSTER_PATH, RELEASE_DATE)
        entity = MovieEntity(ID, TITLE, POSTER_PATH, RELEASE_DATE)
        entityList = listOf(MovieEntity(ID, TITLE, POSTER_PATH, RELEASE_DATE))
    }

    @Test
    fun `movie to movie entity`() {
        movie.toMovieDB()

        assertEquals(ID,movie.id)
        assertEquals(TITLE, movie.title)
        assertEquals(POSTER_PATH, movie.poster_path)
        assertEquals(RELEASE_DATE, movie.release_date)
    }

    @Test
    fun `list of movie entity to movieDB entity list`() {
        val dbToMovieList = entityList.toMovieList()

        for (movie in dbToMovieList){
            assertEquals(ID, movie.id)
            assertEquals(TITLE, movie.title)
            assertEquals(POSTER_PATH, movie.poster_path)
            assertEquals(RELEASE_DATE, movie.release_date)
        }
    }

    @Test
    fun `movieDB entity to movie entity`() {
        val dbToMovieEntity = entity.toMovie()

        assertEquals(ID,dbToMovieEntity.id)
        assertEquals(TITLE, dbToMovieEntity.title)
        assertEquals(POSTER_PATH, dbToMovieEntity.poster_path)
        assertEquals(RELEASE_DATE, dbToMovieEntity.release_date)
    }

    companion object{
        const val ID = 10
        const val TITLE = "Title default"
        const val POSTER_PATH = "/original/movieDefault.jpg"
        const val RELEASE_DATE = "10/2/1999"
    }
}