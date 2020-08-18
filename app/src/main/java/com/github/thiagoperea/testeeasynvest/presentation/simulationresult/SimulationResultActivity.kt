package com.github.thiagoperea.testeeasynvest.presentation.simulationresult

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.github.thiagoperea.testeeasynvest.R
import com.github.thiagoperea.testeeasynvest.data.model.SimulationResult
import com.github.thiagoperea.testeeasynvest.extensions.formatDate
import com.github.thiagoperea.testeeasynvest.extensions.formatMonetary
import com.github.thiagoperea.testeeasynvest.extensions.formatPercent
import kotlinx.android.synthetic.main.activity_simulation_result.*

class SimulationResultActivity : AppCompatActivity() {

    companion object {
        const val RESULT_EXTRA = "result.extra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulation_result)

        resultSimulateAgainButton.setOnClickListener { finish() }
        intent.getParcelableExtra<SimulationResult>(RESULT_EXTRA)?.let(::setupResult)
    }

    private fun setupResult(result: SimulationResult) {
        resultTitleValue.text = result.grossAmount.formatMonetary()

        val subtitleValue = result.grossAmountProfit.formatMonetary()
        val subtitle = getString(R.string.result_subtitle, subtitleValue)
        resultSubtitle.text = paintText(subtitle, subtitleValue)

        resultAppliedValue.text = result.investmentParameter.investedAmount.formatMonetary()
        resultGrossValue.text = result.grossAmount.formatMonetary()
        resultProfitValue.text = result.grossAmountProfit.formatMonetary()
        resultTaxesValue.text = getString(
            R.string.result_taxes,
            result.taxesAmount.formatMonetary(),
            result.taxesRate.formatPercent()
        )
        resultNetAmountValue.text = result.netAmount.formatMonetary()

        resultMaturityDate.text = result.investmentParameter.maturityDate.formatDate(
            "yyyy-MM-dd'T'HH:mm:ss",
            "dd/MM/yyyy"
        )
        resultMaturityTotalDays.text = result.investmentParameter.maturityTotalDays.toString()
        resultMonthlyGrossRate.text = result.monthlyGrossRateProfit.formatPercent()
        resultRatePercent.text = result.investmentParameter.rate.formatPercent()
        resultAnnualGrossRate.text = result.annualGrossRateProfit.formatPercent()
        resultPeriodGrossRate.text = result.rateProfit.formatPercent()
    }

    private fun paintText(text: String, subsString: String): SpannableString {
        val spannable = SpannableString(text)
        val startIdx = text.indexOf(subsString)
        val endIdx = startIdx + subsString.length

        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.colorAccent)),
            startIdx,
            endIdx,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannable
    }
}
