package com.example.mobile_tp6
import com.example.mobile_tp6.database.MovieDatabase
import com.example.mobile_tp6.mvvm.contract.MainContract
import com.example.mobile_tp6.mvvm.viewmodel.MainViewModel
import com.example.mobile_tp6.service.model.Movie
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before

class MainViewModelTest {

    @MockK
    private lateinit var model: MainContract.Model

    private lateinit var viewModel: MainViewModel

    @MockK
    private lateinit var movieList: List<Movie>

    @MockK
    private lateinit var database: MovieDatabase

    @Before
    fun setup(){
        MockKAnnotations.init(this, relaxed = true)
        viewModel = MainViewModel(model)
    }


}