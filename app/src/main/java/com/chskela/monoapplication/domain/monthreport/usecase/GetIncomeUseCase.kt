package com.chskela.monoapplication.domain.monthreport.usecase

import com.chskela.monoapplication.domain.category.models.TypeCategory
import com.chskela.monoapplication.domain.monthreport.repository.MonthReportRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetIncomeUseCase(private val monthReportRepository: MonthReportRepository) {

    operator fun invoke(): Flow<Double> = monthReportRepository.getAllTransactions().map { list ->
        list.fold(0.0) { acc, transactionWithCategory ->
            val amount =
                if (transactionWithCategory.type == TypeCategory.Expense) {
                    0
                } else {
                    transactionWithCategory.amount
                }
            (acc + amount) / 100
        }
    }

}