package com.example.mobile_tp6.presentation.mvvm.contract

import androidx.lifecycle.LiveData
import com.example.mobile_tp6.presentation.mvvm.viewmodel.MainViewModel
import com.example.mobile_tp6.data.service.model.Movie
import com.example.mobile_tp6.util.CoroutineResult
import kotlinx.coroutines.Job

interface MainContract {
    interface Model{
        suspend fun getPopularMovies():CoroutineResult<List<Movie>>
    }
    interface ViewModel{
        fun getValueViewModel(): LiveData<MainViewModel.MainData>
        fun callService():Job
    }
}