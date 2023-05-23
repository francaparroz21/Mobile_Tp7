package com.example.mobile_tp6.usecase

import com.example.mobile_tp6.domain.database.MovieDatabase
import com.example.mobile_tp6.domain.entity.Movie
import com.example.mobile_tp6.domain.service.MovieService
import com.example.mobile_tp6.domain.usecase.GetMoviesUseCase
import com.example.mobile_tp6.domain.usecase.GetMoviesUseCaseImplementation
import com.example.mobile_tp6.util.CoroutineResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetMoviesUseCaseImplementationTest {

    @MockK
    private lateinit var movieService: MovieService

    @MockK
    private lateinit var db: MovieDatabase

    @MockK
    private lateinit var movieList: List<Movie>

    private lateinit var getMoviesUseCase: GetMoviesUseCase

    @Before
    fun init() {
        MockKAnnotations.init(this, relaxed = true)
        getMoviesUseCase = GetMoviesUseCaseImplementation(movieService, db)
    }

    @Test
    fun `when use case return success`() {
        coEvery { movieService.getMovies() } returns CoroutineResult.Success(movieList)
        coEvery { db.getAllMovies() } returns CoroutineResult.Success(movieList)

        val result = runBlocking { getMoviesUseCase() }

        coVerify { db.insertMovies(movieList) }
        coVerify { db.getAllMovies() }

        assertEquals(movieList, (result as CoroutineResult.Success).data)
    }

    @Test
    fun `when use case return failure but the database is not empty`() {
        coEvery { movieService.getMovies() } returns CoroutineResult.Failure(Exception())
        coEvery { db.getAllMovies() } returns CoroutineResult.Success(movieList)

        val result = runBlocking { getMoviesUseCase() }

        coVerify { db.getAllMovies() }

        assertEquals(movieList, (result as CoroutineResult.Success).data)
    }

    @Test
    fun `when use case return failure and the database is empty`() {
        coEvery { movieService.getMovies() } returns CoroutineResult.Failure(Exception())
        coEvery { db.getAllMovies() } returns CoroutineResult.Failure(Exception())

        val result = runBlocking { getMoviesUseCase() }

        coVerify { db.getAllMovies() }

        assertEquals(Exception(), (result as CoroutineResult.Failure).exception)
    }


}