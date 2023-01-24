package com.chskela.monoapplication.domain.reports.usecase

import com.chskela.monoapplication.domain.reports.repository.ReportsRepository
import kotlinx.coroutines.flow.Flow
import java.util.Calendar.*

class GetAmountByCategoryPerMonthUseCase(private val reportsRepository: ReportsRepository) {

    operator fun invoke(categoryId: Long): Flow<Long> {
        val calendar = getInstance()
        calendar.set(DAY_OF_MONTH, 1)
        calendar.set(HOUR_OF_DAY, 0)
        calendar.set(MINUTE, 0)
        calendar.set(SECOND, 0)
        calendar.set(MILLISECOND, 0)
        val startOfMonthInMilliseconds = calendar.timeInMillis
        return reportsRepository.getAmountByCategoryPerMonth(
            categoryId = categoryId,
            startOfMonthInMilliseconds = startOfMonthInMilliseconds
        )
    }
}