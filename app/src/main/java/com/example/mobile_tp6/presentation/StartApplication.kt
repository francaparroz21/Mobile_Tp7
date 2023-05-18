package com.example.mobile_tp6.presentation

import android.app.Application
import com.example.mobile_tp6.di.*
import com.example.mobile_tp6.presentation.di.ModelModule
import com.example.mobile_tp6.presentation.di.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class StartApplication : Application(), KoinComponent{
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@StartApplication)

            modules(
                listOf(
                    ViewModelModule.module,
                    ServiceModule.module,
                    ModelModule.module,
                    UseCaseModule.module,
                    ApiModule.module,
                    DBModule.module,
                    DatabaseModule.module
                )
            )
        }
    }
}