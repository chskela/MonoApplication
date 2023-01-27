package com.chskela.monoapplication.domain.common.usecase

import java.text.NumberFormat
import java.util.*

class CurrencyFormatUseCase {
    private val numberFormat: NumberFormat = NumberFormat.getCurrencyInstance()

    operator fun invoke(letterCode: String): (Double) -> String {
        numberFormat.currency = Currency.getInstance(letterCode)

        return { number -> numberFormat.format(number) }
    }
}