package com.github.thiagoperea.testeeasynvest.usecase

import com.github.thiagoperea.testeeasynvest.data.model.SimulationResult
import com.github.thiagoperea.testeeasynvest.repository.SimulationRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

class SimulationUsecaseTest {

    private lateinit var usecase: SimulationUsecase

    @RelaxedMockK
    lateinit var repositoryMock: SimulationRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        usecase = SimulationUsecase(repositoryMock)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun testExecuteSuccess() = runBlocking {
        val value = 1.0
        val date = "01/01/01"
        val percent = 120
        val onSuccessMock = mockk<(SimulationResult) -> Unit>()

        coEvery { repositoryMock.doSimulation(any(), any(), any()) } returns mockk()

        usecase.execute(value, date, percent, onSuccessMock, {})

        coVerify { onSuccessMock.invoke(any()) }
    }

    @Test
    fun testExecuteError() = runBlocking {
        val value = 1.0
        val date = "01/01/01"
        val percent = 120
        val onErrorMock = mockk<(Exception) -> Unit>()

        coEvery { repositoryMock.doSimulation(any(), any(), any()) } throws Exception()

        usecase.execute(value, date, percent, {}, onErrorMock)

        coVerify { onErrorMock.invoke(any()) }
    }
}