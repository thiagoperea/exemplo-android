package com.github.thiagoperea.testeeasynvest.presentation.startsimulation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
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

        setupTextWatchers()
        setupListeners()
    }

    private fun setupListeners() {
        simulationButton.setOnClickListener { viewModel.startSimulation() }

        viewModel.simulationButtonEnabled.observe(this, Observer {
            simulationButton.isEnabled = it
        })

        viewModel.valueValidationError.observe(this, Observer {
            simulationTotalValue.error = getString(it)
        })
        viewModel.dateValidationError.observe(this, Observer {
            simulationDueDateValue.error = getString(it)
        })
        viewModel.percentValidationError.observe(this, Observer {
            simulationPercentValue.error = getString(it)
        })
    }

    private fun setupTextWatchers() {
        simulationTotalValue.addTextChangedListener(ValueTextWatcher(simulationTotalValue))
        simulationTotalValue.doAfterTextChanged { viewModel.totalValueRaw = it.toString() }

        simulationDueDateValue.addTextChangedListener(DateTextWatcher(simulationDueDateValue))
        simulationDueDateValue.doAfterTextChanged { viewModel.dateRaw = it.toString() }

        simulationPercentValue.addTextChangedListener(PercentTextWatcher(simulationPercentValue))
        simulationPercentValue.doAfterTextChanged { viewModel.percentRaw = it.toString() }
    }
}