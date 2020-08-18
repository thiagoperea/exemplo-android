package com.github.thiagoperea.testeeasynvest.presentation.startsimulation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.github.thiagoperea.testeeasynvest.R
import com.github.thiagoperea.testeeasynvest.presentation.textwatcher.DateTextWatcher
import com.github.thiagoperea.testeeasynvest.presentation.textwatcher.PercentTextWatcher
import com.github.thiagoperea.testeeasynvest.presentation.textwatcher.ValueTextWatcher
import kotlinx.android.synthetic.main.activity_simulation_start.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SimulationStartActivity : AppCompatActivity() {

    private val viewModel: SimulationStartViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulation_start)

        setupTextWatchers()
        setupListeners()
    }

    private fun setupListeners() {
        simulationButton.setOnClickListener { viewModel.startSimulation() }

        viewModel.simulationButtonEnabled.observe(this, {
            simulationButton.isEnabled = it
        })
        viewModel.showLoading.observe(this, {
            if (it) {
                showLoading()
            } else {
                hideLoading()
            }
        })

        viewModel.valueValidationError.observe(this, {
            simulationTotalValue.error = getString(it)
        })
        viewModel.dateValidationError.observe(this, {
            simulationDueDateValue.error = getString(it)
        })
        viewModel.percentValidationError.observe(this, {
            simulationPercentValue.error = getString(it)
        })
    }

    private fun showLoading() {
        simulationButton.isEnabled = false
        simulationLoading.visibility = View.VISIBLE
        simulationTotalHint.visibility = View.GONE
        simulationTotalValue.visibility = View.GONE
        simulationDueDateHint.visibility = View.GONE
        simulationDueDateValue.visibility = View.GONE
        simulationPercentHint.visibility = View.GONE
        simulationPercentValue.visibility = View.GONE
    }

    private fun hideLoading() {
        simulationButton.isEnabled = true
        simulationLoading.visibility = View.GONE
        simulationTotalHint.visibility = View.VISIBLE
        simulationTotalValue.visibility = View.VISIBLE
        simulationDueDateHint.visibility = View.VISIBLE
        simulationDueDateValue.visibility = View.VISIBLE
        simulationPercentHint.visibility = View.VISIBLE
        simulationPercentValue.visibility = View.VISIBLE
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