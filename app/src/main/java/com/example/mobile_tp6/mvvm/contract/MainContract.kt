package com.example.mobile_tp6.mvvm.contract

import androidx.lifecycle.LiveData
import com.example.mobile_tp6.mvvm.viewmodel.MainViewModel
import com.example.mobile_tp6.service.model.Movie
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