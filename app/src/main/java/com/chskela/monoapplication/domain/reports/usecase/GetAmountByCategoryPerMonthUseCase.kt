package com.chskela.monoapplication.domain.reports.usecase

import com.chskela.monoapplication.domain.reports.repository.ReportsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Calendar.*

class GetAmountByCategoryPerMonthUseCase(private val reportsRepository: ReportsRepository) {

    operator fun invoke(categoryId: Long): Flow<Double> {
        val startOfMonth = getInstance()
        startOfMonth.set(DAY_OF_MONTH, 1)
        startOfMonth.set(HOUR_OF_DAY, 0)
        startOfMonth.set(MINUTE, 0)
        startOfMonth.set(SECOND, 0)
        startOfMonth.set(MILLISECOND, 0)
        val startOfMonthInMilliseconds = startOfMonth.timeInMillis
        return reportsRepository.getAmountByCategoryPerMonth(
            categoryId = categoryId,
            startOfMonthInMilliseconds = startOfMonthInMilliseconds
        ).map { it / 100.0 }
    }
}