package com.github.thiagoperea.testeeasynvest.extensions

import org.junit.Assert
import org.junit.Test

class StringExtensionsKtTest {

    @Test
    fun testFormatDate() {
        val input = "01/02/2001"

        val result = input.formatDate("dd/MM/yyyy", "yyyy-MM-dd")
        Assert.assertEquals("2001-02-01", result)
    }
}