package com.chskela.monoapplication.domain.reports.usecase

import com.chskela.monoapplication.domain.reports.repository.ReportsRepository
import com.chskela.monoapplication.domain.transaction.models.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*

class GetAllTransactionsByMonthAndCategoryUseCase(private val reportsRepository: ReportsRepository) {

    operator fun invoke(categoryId: Long, month: Int): Flow<List<Transaction>> {
            val calendar = Calendar.getInstance()
            return reportsRepository.getAllTransactionsByCategory(categoryId).map { list ->
                list.filter {
                    calendar.timeInMillis = it.timestamp.time
                    calendar.get(Calendar.MONTH) == month
                }
            }
        }

}