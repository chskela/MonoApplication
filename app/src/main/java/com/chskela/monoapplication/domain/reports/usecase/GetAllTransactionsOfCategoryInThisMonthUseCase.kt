package com.chskela.monoapplication.domain.reports.usecase

import com.chskela.monoapplication.domain.common.usecase.BeginningOfMonthUseCase
import com.chskela.monoapplication.domain.reports.repository.ReportsRepository
import com.chskela.monoapplication.domain.transaction.models.Transaction
import kotlinx.coroutines.flow.Flow

class GetAllTransactionsOfCategoryInThisMonthUseCase(
    private val reportsRepository: ReportsRepository,
    private val beginningOfMonthUseCase: BeginningOfMonthUseCase
) {

    operator fun invoke(categoryId: Long): Flow<List<Transaction>> {

        return reportsRepository.getAllTransactionsOfCategoryInThisMonth(
            categoryId = categoryId,
            startOfMonth = beginningOfMonthUseCase()
        )
    }
}