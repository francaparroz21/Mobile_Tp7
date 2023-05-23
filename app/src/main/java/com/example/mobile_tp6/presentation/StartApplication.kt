package com.example.mobile_tp6.presentation

import android.app.Application
import com.example.mobile_tp6.di.ServiceModule.serviceModule
import com.example.mobile_tp6.di.UseCaseModule.useCaseModule
import com.example.mobile_tp6.di.ApiModule.apiModule
import com.example.mobile_tp6.di.DatabaseModule.databaseModule
import com.example.mobile_tp6.di.DBModule.dbModule
import com.example.mobile_tp6.presentation.di.ModelModule.modelModule
import com.example.mobile_tp6.presentation.di.ViewModelModule.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class StartApplication : Application(), KoinComponent{
    override fun onCreate(){
        super.onCreate()

        startKoin{
            androidContext(this@StartApplication)

            modules(
                listOf(
                    viewModelModule,
                    serviceModule,
                    modelModule,
                    useCaseModule,
                    apiModule,
                    dbModule,
                    databaseModule
                )
            )
        }
    }
}