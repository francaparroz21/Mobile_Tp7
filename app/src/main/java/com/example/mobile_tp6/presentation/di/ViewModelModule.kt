package com.example.mobile_tp6.presentation.di

import com.example.mobile_tp6.presentation.mvvm.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelModule {
    val module = module {
        viewModel { MainViewModel(get()) }
    }
}