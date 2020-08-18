package com.github.thiagoperea.testeeasynvest.repository

import com.github.thiagoperea.testeeasynvest.data.ApiService
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SimulationRepositoryTest {

    private lateinit var repository: SimulationRepository

    @RelaxedMockK
    lateinit var serviceMock: ApiService

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        repository = SimulationRepository(serviceMock)
    }

    @Test
    fun testDoSimulation() = runBlocking {
        val value = 1.0
        val date = "01/01/01"
        val percent = 120

        repository.doSimulation(value, date, percent)

        coVerify {
            serviceMock.doSimulation(
                totalValue = value,
                maturityDate = date,
                rate = percent
            )
        }
    }
}