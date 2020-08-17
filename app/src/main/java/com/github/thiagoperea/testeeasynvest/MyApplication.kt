package com.github.thiagoperea.testeeasynvest

import android.app.Application
import com.github.thiagoperea.testeeasynvest.presentation.startsimulation.SimulationStartViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(
                viewModelModule,
                networkModule
            )
        }
    }
}

val viewModelModule = module {
    viewModel { SimulationStartViewModel() }
}