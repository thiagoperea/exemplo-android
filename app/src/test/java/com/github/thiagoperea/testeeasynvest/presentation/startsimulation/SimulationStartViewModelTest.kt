package com.github.thiagoperea.testeeasynvest.presentation.startsimulation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.thiagoperea.testeeasynvest.R
import com.github.thiagoperea.testeeasynvest.data.model.SimulationResult
import com.github.thiagoperea.testeeasynvest.usecase.SimulationUsecase
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.slot
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SimulationStartViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: SimulationStartViewModel

    @RelaxedMockK
    lateinit var usecaseMock: SimulationUsecase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = SimulationStartViewModel(usecaseMock)
    }

    @Test
    fun testValidateFieldsDisabled() {
        viewModel.validateFields()

        Assert.assertEquals(false, viewModel.simulationButtonEnabled.value)
    }

    @Test
    fun testValidateFieldsEnabled() {
        viewModel.totalValueRaw = "123"
        viewModel.dateRaw = "123"
        viewModel.percentRaw = "123"

        viewModel.validateFields()

        Assert.assertEquals(true, viewModel.simulationButtonEnabled.value)
    }

    @Test
    fun testGetFormattedPercentInvalid() {
        viewModel.percentRaw = "0"

        viewModel.getFormattedPercent()

        Assert.assertEquals(
            R.string.percent_validation_error,
            viewModel.percentValidationError.value
        )
    }

    @Test
    fun testGetFormattedPercentValid() {
        viewModel.percentRaw = "10"

        val result = viewModel.getFormattedPercent()

        Assert.assertEquals(10, result)
    }

    @Test
    fun testGetFormattedValueInvalid() {
        viewModel.totalValueRaw = "R$ 0.00"

        viewModel.getFormattedValue()

        Assert.assertEquals(
            R.string.value_validation_error,
            viewModel.valueValidationError.value
        )
    }

    @Test
    fun testGetFormattedValueValid() {
        viewModel.totalValueRaw = "R$ 0.20"

        val result = viewModel.getFormattedValue()

        Assert.assertEquals(0.2, result)
    }

    @Test
    fun testGetFormattedDateInvalid() {
        viewModel.dateRaw = "40/01/2020"

        viewModel.getFormattedDate()

        Assert.assertEquals(
            R.string.date_validation_error,
            viewModel.dateValidationError.value
        )
    }

    @Test
    fun testGetFormattedDateInvalidLength() {
        viewModel.dateRaw = "30/01/2"

        viewModel.getFormattedDate()

        Assert.assertEquals(
            R.string.date_validation_error,
            viewModel.dateValidationError.value
        )
    }

    @Test
    fun testGetFormattedDateValid() {
        viewModel.dateRaw = "20/02/2020"

        val result = viewModel.getFormattedDate()

        Assert.assertEquals("2020-02-20", result)
    }

    @Test
    fun testStartSimulationSuccess() {
        val argCaptor = slot<(SimulationResult) -> Unit>()
        viewModel.totalValueRaw = "R$ 4.90"
        viewModel.dateRaw = "02/04/2020"
        viewModel.percentRaw = "130"

        viewModel.startSimulation()

        Assert.assertEquals(true, viewModel.showLoading.value)
        coVerify {
            usecaseMock.execute(any(), any(), any(), capture(argCaptor), any())
        }

        argCaptor.captured.invoke(mockk())
        Assert.assertEquals(false, viewModel.showLoading.value)
        Assert.assertNotNull(viewModel.simulationResult.value)
    }

    @Test
    fun testStartSimulationError() {
        val argCaptor = slot<(Exception) -> Unit>()
        viewModel.totalValueRaw = "R$ 4.90"
        viewModel.dateRaw = "02/04/2020"
        viewModel.percentRaw = "130"

        viewModel.startSimulation()

        Assert.assertEquals(true, viewModel.showLoading.value)
        coVerify {
            usecaseMock.execute(any(), any(), any(), any(), capture(argCaptor))
        }

        val exception = Exception("message")
        argCaptor.captured.invoke(exception)
        Assert.assertEquals(false, viewModel.showLoading.value)
        Assert.assertEquals(exception.message, viewModel.simulationError.value)
    }
}