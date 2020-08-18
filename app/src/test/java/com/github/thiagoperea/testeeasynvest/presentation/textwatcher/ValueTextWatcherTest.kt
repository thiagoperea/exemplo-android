package com.github.thiagoperea.testeeasynvest.presentation.textwatcher

import android.app.Activity
import android.widget.EditText
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ValueTextWatcherTest {

    @Test
    fun testMask() {
        val activity = Robolectric.buildActivity(Activity::class.java)
        val editText = EditText(activity.get())
        editText.addTextChangedListener(ValueTextWatcher(editText))

        editText.setText("495075")
        Assert.assertEquals("BRL 4,950.75", editText.text.toString())
    }
}