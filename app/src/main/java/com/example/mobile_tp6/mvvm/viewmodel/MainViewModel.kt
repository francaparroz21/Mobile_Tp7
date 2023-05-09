package com.example.mobile_tp6.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_tp6.mvvm.contract.MainContract
import com.example.mobile_tp6.service.model.Movie
import com.example.mobile_tp6.util.CoroutineResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val model: MainContract.Model) : ViewModel(), MainContract.ViewModel {
    data class MainData(
        var status: MainStatus,
        val movies: List<Movie> = listOf(),
        var exception: Exception? = null
    )

    enum class MainStatus {
        SHOW_INFO,
        ERROR
    }

    private val mutableLiveData: MutableLiveData<MainData> = MutableLiveData()
    override fun getValueViewModel(): LiveData<MainData> = mutableLiveData

    override fun callService() = viewModelScope.launch {
        withContext(Dispatchers.IO) { model.getPopularMovies() }.let { result ->
            when (result) {
                is CoroutineResult.Success -> {
                    mutableLiveData.value = MainData(MainStatus.SHOW_INFO, result.data)
                }
                is CoroutineResult.Failure -> {
                    mutableLiveData.value = MainData(MainStatus.ERROR, exception = result.exception)
                }
            }
        }
    }


}