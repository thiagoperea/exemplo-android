package com.github.thiagoperea.testeeasynvest.extensions

import org.junit.Assert
import org.junit.Test

class DoubleExtensionsKtTest {

    @Test
    fun testFormatMonetary() {
        val input = 10.0
        val result = input.formatMonetary()

        Assert.assertEquals("BRL 10.00", result)
    }

    @Test
    fun testFormatPercent() {
        val input = 27.3795
        val result = input.formatPercent()

        Assert.assertEquals("27.38%", result)
    }
}