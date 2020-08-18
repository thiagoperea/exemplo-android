package com.github.thiagoperea.testeeasynvest

import com.github.thiagoperea.testeeasynvest.presentation.startsimulation.SimulationStartViewModel
import com.github.thiagoperea.testeeasynvest.repository.SimulationRepository
import com.github.thiagoperea.testeeasynvest.usecase.SimulationUsecase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SimulationStartViewModel(get()) }
}

val usecaseModule = module {
    factory { SimulationUsecase(get()) }
}

val repositoryModule = module {
    single { SimulationRepository(get()) }
}