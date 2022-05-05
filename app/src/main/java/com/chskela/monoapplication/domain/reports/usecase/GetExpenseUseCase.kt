package com.chskela.monoapplication.domain.reports.usecase

import com.chskela.monoapplication.domain.category.models.TypeCategory
import com.chskela.monoapplication.domain.reports.repository.ReportsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetExpenseUseCase(private val reportsRepository: ReportsRepository) {

    operator fun invoke(): Flow<Double> = reportsRepository.getAllTransactionsByCategory().map { list ->
        list.fold(0.0) { acc, transactionWithCategory ->
            val amount = if (transactionWithCategory.type == TypeCategory.Expense) {
                transactionWithCategory.amount
            } else {
                0
            }
            acc + amount
        }
    }
}