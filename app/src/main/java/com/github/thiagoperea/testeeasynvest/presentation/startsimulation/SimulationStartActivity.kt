package com.github.thiagoperea.testeeasynvest.presentation.startsimulation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.thiagoperea.testeeasynvest.R
import com.github.thiagoperea.testeeasynvest.presentation.textwatcher.DateTextWatcher
import com.github.thiagoperea.testeeasynvest.presentation.textwatcher.PercentTextWatcher
import com.github.thiagoperea.testeeasynvest.presentation.textwatcher.ValueTextWatcher
import kotlinx.android.synthetic.main.activity_simulation_start.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SimulationStartActivity : AppCompatActivity() {

    val viewModel: SimulationStartViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulation_start)

        simulationTotalValue.addTextChangedListener(ValueTextWatcher(simulationTotalValue))
        simulationDueDateValue.addTextChangedListener(DateTextWatcher(simulationDueDateValue))
        simulationPercentValue.addTextChangedListener(PercentTextWatcher(simulationPercentValue))
    }
}
