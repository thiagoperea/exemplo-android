package com.github.thiagoperea.testeeasynvest.presentation.simulationresult

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.thiagoperea.testeeasynvest.R
import com.github.thiagoperea.testeeasynvest.data.model.SimulationResult
import kotlinx.android.synthetic.main.activity_simulation_result.*

class SimulationResultActivity : AppCompatActivity() {

    companion object {
        const val RESULT_EXTRA = "result.extra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulation_result)

        intent.getParcelableExtra<SimulationResult>(RESULT_EXTRA)?.let(::setupResult)
    }

    private fun setupResult(result: SimulationResult) {
        resultTitleValue.text = result.grossAmount.toString()
        resultSubtitle.text = getString(R.string.result_subtitle, result.grossAmountProfit.toString())
    }

}
