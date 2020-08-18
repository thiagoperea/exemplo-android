package com.github.thiagoperea.testeeasynvest.presentation.textwatcher

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.github.thiagoperea.testeeasynvest.extensions.formatMonetary

class ValueTextWatcher(private val editText: EditText) : TextWatcher {

    override fun afterTextChanged(str: Editable) {
        editText.removeTextChangedListener(this)

        val value = str.toString().filter { it.isDigit() }

        if (value.isNotEmpty()) {
            str.clear()
            val doubleVal = value.toDouble() / 100
            str.append(doubleVal.formatMonetary())
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