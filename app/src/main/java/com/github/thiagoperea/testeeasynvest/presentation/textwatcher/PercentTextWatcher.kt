package com.github.thiagoperea.testeeasynvest.presentation.textwatcher

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class PercentTextWatcher(private val editText: EditText) : TextWatcher {

    private var isDeletion = false

    override fun afterTextChanged(str: Editable) {
        editText.removeTextChangedListener(this)

        val unmasked = str.toString().replace("%", "")

        str.clear()
        if (isDeletion) {
            str.append(unmasked.dropLast(1))
        } else {
            str.append(unmasked)
        }

        if (str.isNotEmpty()) {
            str.append('%')
        }

        editText.setSelection(str.length)
        editText.addTextChangedListener(this)
    }

    override fun beforeTextChanged(str: CharSequence, start: Int, count: Int, after: Int) {
        // do nothing
    }

    override fun onTextChanged(str: CharSequence, start: Int, before: Int, count: Int) {
        isDeletion = before > 0 && count == 0
    }
}