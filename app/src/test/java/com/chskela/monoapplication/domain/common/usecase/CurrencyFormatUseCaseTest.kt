package com.chskela.monoapplication.domain.common.usecase

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.*

class CurrencyFormatUseCaseTest {

    lateinit var currencyFormatUseCase: CurrencyFormatUseCase

    @Before
    fun setUp() {
        currencyFormatUseCase = CurrencyFormatUseCase(Locale("ru"))
    }

    @Test
    fun `Currency is correctly formatted`() {
        val letterCode = "RUB"
        val value = 1234.56

        val actual = "1 234,56 ₽"
        val expected = currencyFormatUseCase(letterCode)(value)
        assertEquals(expected, actual)
    }

    @Test
    fun `If letterCode is not a supported ISO 4217 code, an IllegalArgumentException is thrown`() {
        val letterCode = "incorrect"
        val value = 1234.56

        val exception = assertThrows(IllegalArgumentException::class.java) {
            currencyFormatUseCase(letterCode)(value)
        }

        val expectedMessage = "letterCode is not a supported ISO 4217 code"
        val actualMessage: String = exception.message ?: ""

        assertTrue(actualMessage.contains(expectedMessage))
    }
}