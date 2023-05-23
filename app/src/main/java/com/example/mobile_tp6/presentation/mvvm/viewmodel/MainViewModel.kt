package com.example.mobile_tp6.presentation.mvvm.viewmodel
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mobile_tp6.presentation.activity.MainActivity
import com.example.mobile_tp6.presentation.activity.MoviesActivity

class MainViewModel :ViewModel(){

    private val mutableLiveData: MutableLiveData<MainStatus> = MutableLiveData()

    fun getValue():LiveData<MainStatus> = mutableLiveData

    fun buttonPressed(){
        mutableLiveData.value = MainStatus.MOVIES
    }
    enum class MainStatus {
        MAIN,
        MOVIES
    }

    init {
        mutableLiveData.value = MainStatus.MAIN
    }

}