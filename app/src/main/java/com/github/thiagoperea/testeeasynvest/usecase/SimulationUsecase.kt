package com.github.thiagoperea.testeeasynvest.usecase

import com.github.thiagoperea.testeeasynvest.data.model.SimulationResult
import com.github.thiagoperea.testeeasynvest.repository.SimulationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SimulationUsecase(private val repository: SimulationRepository) {

    fun execute(
        value: Double,
        date: String,
        percent: Int,
        onSuccess: (SimulationResult) -> Unit,
        onError: (Exception) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = repository.doSimulation(value, date, percent)

                withContext(Dispatchers.Main) { onSuccess(result) }
            } catch (error: Exception) {
                withContext(Dispatchers.Main) { onError(error) }
            }
        }
    }
}