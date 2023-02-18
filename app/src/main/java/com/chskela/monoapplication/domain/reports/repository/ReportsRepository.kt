package com.chskela.monoapplication.domain.reports.repository

import com.chskela.monoapplication.domain.reports.models.TransactionWithCategory
import com.chskela.monoapplication.domain.transaction.models.Transaction
import kotlinx.coroutines.flow.Flow
import java.util.*

interface ReportsRepository {
    fun getAllTransactionsWithCategory(): Flow<List<TransactionWithCategory>>

    fun getAllTransactionsByCategory(categoryId: Long): Flow<List<Transaction>>

    fun getAmountByCategoryPerMonth(
        categoryId: Long,
        startOfMonth: Date
    ): Flow<Long>
}