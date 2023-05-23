package com.pil.movieApp.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mobile_tp6.domain.entity.Movie
import com.example.mobile_tp6.presentation.mvvm.model.MainModel
import com.example.mobile_tp6.presentation.mvvm.viewmodel.MainViewModel
import com.example.mobile_tp6.util.CoroutineResult
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MainViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    @MockK
    private lateinit var model: MainModel

    @MockK
    private lateinit var movieList: List<Movie>


    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = MainViewModel(model)
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `SHOW_INFO status when we obtain the movies is successful`() {
        coEvery { model.getMovies() } returns CoroutineResult.Success(movieList)

        runBlocking { viewModel.callService().join() }

        assertEquals(movieList, viewModel.getValueViewModel().value?.movies)
        assertEquals(MainViewModel.MainStatus.SHOW_INFO, viewModel.getValueViewModel().value?.status)
    }

    @Test
    fun `ERROR status when the call of service is failure`() {
        coEvery { model.getMovies() } returns CoroutineResult.Failure(Exception())

        runBlocking { viewModel.callService().join() }

        assertEquals(MainViewModel.MainStatus.ERROR, viewModel.getValueViewModel().value?.status)
    }


}