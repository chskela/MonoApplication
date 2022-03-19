package com.chskela.monoapplication.domain.monthreport.usecase

import com.chskela.monoapplication.domain.monthreport.models.TransactionWithCategory
import com.chskela.monoapplication.domain.monthreport.repository.MonthReportRepository
import kotlinx.coroutines.flow.Flow

class GetAllTransactionsUseCase(private val monthReportRepository: MonthReportRepository) {

    operator fun invoke(): Flow<List<TransactionWithCategory>> =
        monthReportRepository.getAllTransactions()
}