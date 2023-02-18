package com.chskela.monoapplication.domain.reports.usecase

import com.chskela.monoapplication.domain.reports.models.TransactionWithCategory
import com.chskela.monoapplication.domain.reports.repository.ReportsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*

class GetAllTransactionsByMonthUseCase(private val reportsRepository: ReportsRepository) {

    operator fun invoke(month: Int): Flow<List<TransactionWithCategory>> {
        val calendar = Calendar.getInstance()
        return reportsRepository.getAllTransactionsWithCategory().map { list ->
            list.filter {
                calendar.time = it.timestamp
                calendar.get(Calendar.MONTH) == month
            }
        }
    }

}