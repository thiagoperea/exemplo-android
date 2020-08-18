package com.github.thiagoperea.testeeasynvest

import com.github.thiagoperea.testeeasynvest.data.ApiService
import com.github.thiagoperea.testeeasynvest.data.model.SimulationResult
import com.github.thiagoperea.testeeasynvest.data.model.SimulationResultParameters
import kotlinx.coroutines.delay
import org.koin.dsl.bind
import org.koin.dsl.module

val networkModule = module {

    single { MockApi() } bind ApiService::class
}

class MockApi : ApiService {

    override suspend fun doSimulation(
        totalValue: Double,
        index: String,
        rate: Int,
        isTaxFree: Boolean,
        maturityDate: String
    ): SimulationResult {

        // fake loading
        val loadingTime = (500..2000).random().toLong()
        delay(loadingTime)

        // Error case
        if (rate < 100) {
            throw RuntimeException("Teste de cenário de erro!!")
        }

        // Success case
        val resultParameters = SimulationResultParameters(
            totalValue,
            yearlyInterestRate = 9.5512,
            maturityTotalDays = 1981,
            maturityBusinessDays = 1409,
            maturityDate = maturityDate.plus("T00:00:00"), //using same date
            rate = rate.toDouble(),
            isTaxFree = false
        )

        return SimulationResult(
            resultParameters,
            60528.20,
            4230.78,
            56297.42,
            28205.20,
            23974.42,
            87.26,
            0.76,
            0.000445330025305748,
            15.0,
            9.5512,
            74.17
        )
    }
}