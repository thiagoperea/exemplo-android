package com.github.thiagoperea.testeeasynvest.repository

import com.github.thiagoperea.testeeasynvest.data.ApiService

class SimulationRepository(private val service: ApiService) {

    suspend fun doSimulation(value: Double, date: String, percent: Int) = service.doSimulation(
        totalValue = value,
        rate = percent,
        maturityDate = date
    )
}