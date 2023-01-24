package com.chskela.monoapplication.domain.reports.usecase

import com.chskela.monoapplication.domain.reports.models.TransactionWithCategory
import com.chskela.monoapplication.domain.reports.repository.ReportsRepository
import kotlinx.coroutines.flow.Flow

class GetAllTransactionsUseCase(private val reportsRepository: ReportsRepository) {

    operator fun invoke(): Flow<List<TransactionWithCategory>> =
        reportsRepository.getAllTransactionsWithCategory()
}