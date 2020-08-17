package com.github.thiagoperea.testeeasynvest.presentation.textwatcher

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class DateTextWatcher(private val editText: EditText) : TextWatcher {

    private var isDeletion: Boolean = false

    override fun afterTextChanged(str: Editable) {
        if (isDeletion) return

        editText.removeTextChangedListener(this)

        val unmasked = str.toString().replace("/", "")

        str.clear()
        str.append(unmasked)

        if (str.length > 2) {
            str.insert(2, "/")

            if (str.length > 5) {
                str.insert(5, "/")
            }
        }

        editText.addTextChangedListener(this)
    }

    override fun beforeTextChanged(str: CharSequence, start: Int, count: Int, after: Int) {
        // do nothing
    }

    override fun onTextChanged(str: CharSequence, start: Int, before: Int, count: Int) {
        isDeletion = before > 0 && count == 0
    }
}