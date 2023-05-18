package com.example.mobile_tp6
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mobile_tp6.presentation.mvvm.contract.MainContract
import com.example.mobile_tp6.presentation.mvvm.viewmodel.MainViewModel
import com.example.mobile_tp6.data.service.model.Movie
import com.example.mobile_tp6.util.CoroutineResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Exception
import kotlin.test.assertEquals

class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @MockK
    private lateinit var model: MainContract.Model

    private lateinit var viewModel: MainViewModel

    @MockK
    private lateinit var movieList: List<Movie>

    @Before
    @OptIn(ExperimentalCoroutinesApi::class)
    fun setup(){
        Dispatchers.setMain(UnconfinedTestDispatcher())
        MockKAnnotations.init(this, relaxed = true)
        viewModel = MainViewModel(model)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `when the call get popular movies is successful, the view-model state is (SHOW_INFO)`(){
        coEvery { model.getPopularMovies() }returns CoroutineResult.Success(movieList)

        runBlocking { viewModel.callService().join() }

        assert(movieList == viewModel.getValueViewModel().value?.movies)
        assertEquals(MainViewModel.MainStatus.SHOW_INFO, viewModel.getValueViewModel().value?.status)
    }

    @Test
    fun `Error view-model status works`(){
        coEvery { model.getPopularMovies() }returns CoroutineResult.Failure(Exception())

        runBlocking { viewModel.callService().join() }

        assertEquals(MainViewModel.MainStatus.ERROR, viewModel.getValueViewModel().value?.status)
    }
}