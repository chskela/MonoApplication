package com.chskela.monoapplication.domain.common.usecase

import java.util.*

class BeginningOfMonthUseCase {

    operator fun invoke(startOfMonth: Calendar = Calendar.getInstance()): Date {
        startOfMonth.set(Calendar.DAY_OF_MONTH, 1)
        startOfMonth.set(Calendar.HOUR_OF_DAY, 0)
        startOfMonth.set(Calendar.MINUTE, 0)
        startOfMonth.set(Calendar.SECOND, 0)
        startOfMonth.set(Calendar.MILLISECOND, 0)
        return startOfMonth.time
    }
}