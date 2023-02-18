package com.chskela.monoapplication.data.reports.repository

import com.chskela.monoapplication.data.reports.storage.dao.ReportsDao
import com.chskela.monoapplication.domain.common.repository.AbstractRepository
import com.chskela.monoapplication.domain.reports.models.TransactionWithCategory
import com.chskela.monoapplication.domain.reports.repository.ReportsRepository
import com.chskela.monoapplication.domain.transaction.models.Transaction
import com.chskela.monoapplication.mappers.mapToDomain
import com.chskela.monoapplication.mappers.mapToTransactionWithCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.util.*

class ReportsRepositoryImpl(private val reportsDao: ReportsDao) : ReportsRepository,
    AbstractRepository() {

    override fun getAllTransactionsWithCategory(): Flow<List<TransactionWithCategory>> {
        return reportsDao.getAllTransactionsEntityWithCategory()
            .distinctUntilChanged()
            .map { list ->
                list.map { it.mapToTransactionWithCategory() }
            }.flowOn(coroutineContext)
    }

    override fun getAllTransactionsByCategory(categoryId: Long): Flow<List<Transaction>> {
        return reportsDao.getAllTransactionsByCategory(categoryId)
            .distinctUntilChanged()
            .map { list ->
                list.map { it.mapToDomain() }
            }.flowOn(coroutineContext)
    }

    override fun getAllTransactionsOfCategoryInThisMonth(
        categoryId: Long,
        startOfMonth: Date
    ): Flow<List<Transaction>> {
        return reportsDao.getAllTransactionsOfCategoryInThisMonth(categoryId, startOfMonth)
            .distinctUntilChanged()
            .map { list ->
                list.map { it.mapToDomain() }
            }.flowOn(coroutineContext)
    }

    override fun getAmountByCategoryPerMonth(categoryId: Long, startOfMonth: Date): Flow<Long> {
        return reportsDao
            .getAmountByCategoryPerMonth(categoryId, startOfMonth)
            .distinctUntilChanged()
            .flowOn(coroutineContext)
    }
}
