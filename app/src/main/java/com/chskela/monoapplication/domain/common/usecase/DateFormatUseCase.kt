package com.chskela.monoapplication.domain.common.usecase

import java.text.SimpleDateFormat
import java.util.*

class DateFormatUseCase {
    private val utc = TimeZone.getTimeZone("UTC")

    operator fun invoke(pattern: String = "dd MMMM yyyy (EEEE)"): (Date) -> String {

        val formatter = SimpleDateFormat(pattern, Locale.getDefault())
            .apply {
                timeZone = utc
            }

        return { date -> formatter.format(date) }
    }
}