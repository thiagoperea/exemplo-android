package com.github.thiagoperea.testeeasynvest.presentation.textwatcher

import android.app.Activity
import android.widget.EditText
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class DateTextWatcherTest {

    @Test
    fun testMask() {
        val activity = Robolectric.buildActivity(Activity::class.java)
        val editText = EditText(activity.get())
        editText.addTextChangedListener(DateTextWatcher(editText))

        editText.setText("01202020")
        Assert.assertEquals("01/20/2020", editText.text.toString())
    }
}