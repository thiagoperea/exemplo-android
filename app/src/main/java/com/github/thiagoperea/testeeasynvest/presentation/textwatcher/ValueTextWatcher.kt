package com.github.thiagoperea.testeeasynvest.presentation.textwatcher

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.text.NumberFormat
import java.util.*

class ValueTextWatcher(private val editText: EditText) : TextWatcher {

    override fun afterTextChanged(str: Editable) {
        editText.removeTextChangedListener(this)

        val value = str.toString().filter { it.isDigit() }
        if (value.isNotEmpty()) {

            val numberFormat = NumberFormat.getCurrencyInstance()
                .apply {
                    maximumFractionDigits = 5
                    currency = Currency.getInstance(Locale("pt", "BR"))
                }

            str.clear()
            str.append(numberFormat.format(value.toDouble() / 100))
        }

        editText.setSelection(str.length)
        editText.addTextChangedListener(this)
    }

    override fun beforeTextChanged(str: CharSequence, start: Int, count: Int, after: Int) {
        // do nothing
    }

    override fun onTextChanged(str: CharSequence, start: Int, before: Int, count: Int) {
        // do nothing
    }
}