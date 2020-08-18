package com.github.thiagoperea.testeeasynvest.data

import com.github.thiagoperea.testeeasynvest.data.model.SimulationResult
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("calculator/simulate")
    suspend fun doSimulation(
        @Query("investedAmout") totalValue: Double,
        @Query("index") index: String = "CDI",
        @Query("rate") rate: Int,
        @Query("isTaxFree") isTaxFree: Boolean = false,
        @Query("maturityDate") maturityDate: String
    ): SimulationResult
}