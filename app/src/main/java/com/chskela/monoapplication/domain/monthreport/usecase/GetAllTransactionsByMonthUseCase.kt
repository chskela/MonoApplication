package com.chskela.monoapplication.domain.monthreport.usecase

import com.chskela.monoapplication.domain.monthreport.models.TransactionWithCategory
import com.chskela.monoapplication.domain.monthreport.repository.MonthReportRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*

class GetAllTransactionsByMonthUseCase(private val monthReportRepository: MonthReportRepository) {

    operator fun invoke(month: Int): Flow<List<TransactionWithCategory>> {
        val calendar = Calendar.getInstance()
        return monthReportRepository.getAllTransactions().map { list ->
            list.filter {
                calendar.timeInMillis = it.timestamp
                calendar.get(Calendar.MONTH) == month
            }
        }
    }

}