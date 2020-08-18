package com.github.thiagoperea.testeeasynvest.extensions

import java.text.SimpleDateFormat
import java.util.*

fun String.formatDate(inputFormat: String, outputFormat: String): String {
    val inDF = SimpleDateFormat(inputFormat, Locale.getDefault())
    inDF.isLenient = false
    val inputDate = inDF.parse(this)

    val outDF = SimpleDateFormat(outputFormat, Locale.getDefault())
    return outDF.format(inputDate)
}