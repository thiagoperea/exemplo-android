package com.github.thiagoperea.testeeasynvest.data

import com.github.thiagoperea.testeeasynvest.data.model.SimulationResult
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("calculator/simulate")
    fun doSimulation(
        @Path("investedAmout") totalValue: Double,
        @Path("index") index: String = "CDI",
        @Path("rate") rate: Int,
        @Path("isTaxFree") isTaxFree: Boolean = false,
        @Path("maturityDate") maturityDate: String
    ): SimulationResult
}