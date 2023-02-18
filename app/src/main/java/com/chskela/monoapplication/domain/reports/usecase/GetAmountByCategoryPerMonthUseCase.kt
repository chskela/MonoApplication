package com.chskela.monoapplication.domain.reports.usecase

import com.chskela.monoapplication.domain.common.usecase.BeginningOfMonthUseCase
import com.chskela.monoapplication.domain.reports.repository.ReportsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAmountByCategoryPerMonthUseCase(
    private val reportsRepository: ReportsRepository,
    private val beginningOfMonthUseCase: BeginningOfMonthUseCase
    ) {

    operator fun invoke(categoryId: Long): Flow<Double> {

        return reportsRepository.getAmountByCategoryPerMonth(
            categoryId = categoryId,
            startOfMonth = beginningOfMonthUseCase()
        ).map { it / 100.0 }
    }
}