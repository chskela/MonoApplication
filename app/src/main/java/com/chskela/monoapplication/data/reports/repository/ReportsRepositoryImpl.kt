package com.chskela.monoapplication.data.reports.repository

import com.chskela.monoapplication.data.reports.storage.dao.ReportsDao
import com.chskela.monoapplication.domain.reports.models.TransactionWithCategory
import com.chskela.monoapplication.domain.reports.repository.ReportsRepository
import com.chskela.monoapplication.domain.transaction.models.Transaction
import com.chskela.monoapplication.mappers.mapToTransaction
import com.chskela.monoapplication.mappers.mapToTransactionWithCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class ReportsRepositoryImpl(private val reportsDao: ReportsDao) :
    ReportsRepository {

    override fun getAllTransactionsWithCategory(): Flow<List<TransactionWithCategory>> {
        return reportsDao.getAllTransactionsEntityWithCategory()
            .distinctUntilChanged()
            .map { list ->
                list.map { it.mapToTransactionWithCategory() }
            }
    }

    override fun getAllTransactionsByCategory(categoryId: Long): Flow<List<Transaction>> {
        return reportsDao.getAllTransactionsByCategory(categoryId)
            .distinctUntilChanged()
            .map { list ->
                list.map { it.mapToTransaction() }
            }
    }
}
