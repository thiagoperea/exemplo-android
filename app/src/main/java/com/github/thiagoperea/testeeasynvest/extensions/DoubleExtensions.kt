package com.github.thiagoperea.testeeasynvest.extensions

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

fun Double.formatMonetary(): String {
    val numberFormat = (NumberFormat.getCurrencyInstance() as DecimalFormat).apply {
        val locale = Locale("pt", "BR")
        val symbol = Currency.getInstance(locale).symbol
        isGroupingUsed = true
        positivePrefix = "$symbol "
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }
    return numberFormat.format(this)
}

fun Double.formatPercent() = String.format("%.2f", this).plus('%')
