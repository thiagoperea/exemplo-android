package com.github.thiagoperea.testeeasynvest.presentation.textwatcher

import android.app.Activity
import android.widget.EditText
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class PercentTextWatcherTest {

    @Test
    fun testMask() {
        val activity = Robolectric.buildActivity(Activity::class.java)
        val editText = EditText(activity.get())
        editText.addTextChangedListener(PercentTextWatcher(editText))

        editText.setText("1400")
        Assert.assertEquals("1400%", editText.text.toString())
    }
}