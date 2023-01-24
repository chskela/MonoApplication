package com.chskela.monoapplication.domain.reports.repository

import com.chskela.monoapplication.domain.reports.models.TransactionWithCategory
import com.chskela.monoapplication.domain.transaction.models.Transaction
import kotlinx.coroutines.flow.Flow

interface ReportsRepository {
    fun getAllTransactionsWithCategory(): Flow<List<TransactionWithCategory>>

    fun getAllTransactionsByCategory(categoryId: Long): Flow<List<Transaction>>

    fun getAmountByCategoryPerMonth(
        categoryId: Long,
        startOfMonthInMilliseconds: Long
    ): Flow<Long>
}