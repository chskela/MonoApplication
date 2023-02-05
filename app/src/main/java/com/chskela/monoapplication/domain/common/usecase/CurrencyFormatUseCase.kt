package com.chskela.monoapplication.domain.common.usecase

import java.text.NumberFormat
import java.util.*

class CurrencyFormatUseCase(locale: Locale = Locale("ru")) {
    private val numberFormat: NumberFormat = NumberFormat.getCurrencyInstance(locale)

    @Throws(IllegalArgumentException::class)
    operator fun invoke(letterCode: String): (Double) -> String {
        try {
            numberFormat.currency = Currency.getInstance(letterCode)
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("letterCode is not a supported ISO 4217 code")
        }


        return { number -> numberFormat.format(number) }
    }
}