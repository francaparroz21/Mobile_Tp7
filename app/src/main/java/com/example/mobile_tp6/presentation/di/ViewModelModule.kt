package com.example.mobile_tp6.presentation.di

import com.example.mobile_tp6.presentation.mvvm.viewmodel.MainViewModel
import com.example.mobile_tp6.presentation.mvvm.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelModule {
    val movieViewModelModule = module {
        viewModel { MovieViewModel(get()) }
    }

    val mainViewModelModule = module {
        viewModel { MainViewModel() }
    }
}