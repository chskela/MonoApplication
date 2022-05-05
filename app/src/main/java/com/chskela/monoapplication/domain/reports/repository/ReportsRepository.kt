package com.chskela.monoapplication.domain.reports.repository

import com.chskela.monoapplication.domain.reports.models.TransactionWithCategory
import kotlinx.coroutines.flow.Flow

interface ReportsRepository {
    fun getAllTransactions(): Flow<List<TransactionWithCategory>>

    fun getAllTransactionsByCategory(): Flow<List<TransactionWithCategory>>
}