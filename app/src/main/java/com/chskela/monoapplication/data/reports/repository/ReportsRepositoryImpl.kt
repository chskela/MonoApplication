package com.chskela.monoapplication.data.reports.repository

import com.chskela.monoapplication.data.category.storage.models.Type
import com.chskela.monoapplication.data.reports.storage.dao.ReportsDao
import com.chskela.monoapplication.data.reports.storage.models.TransactionEntityWithCategory
import com.chskela.monoapplication.data.transaction.storage.models.TransactionEntity
import com.chskela.monoapplication.domain.category.models.TypeCategory
import com.chskela.monoapplication.domain.reports.models.TransactionWithCategory
import com.chskela.monoapplication.domain.reports.repository.ReportsRepository
import com.chskela.monoapplication.domain.transaction.models.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ReportsRepositoryImpl(private val reportsDao: ReportsDao) :
    ReportsRepository {

    override fun getAllTransactions(): Flow<List<TransactionWithCategory>> {
        return reportsDao.getAllTransactions().map { list ->
            list.map { mapToDomain(it) }
        }
    }

    override fun getAllTransactionsByCategory(categoryId: Long): Flow<List<Transaction>> {
        return reportsDao.getAllTransactionsByCategory(categoryId).map { list ->
            list.map { mapTransactionToDomain(it) }
        }
    }

    private fun mapTypeToDomain(type: Type): TypeCategory = when (type) {
        Type.Expense -> TypeCategory.Expense
        Type.Income -> TypeCategory.Income
    }

    private fun mapToDomain(transactionEntityWithCategory: TransactionEntityWithCategory): TransactionWithCategory =
        TransactionWithCategory(
            id = transactionEntityWithCategory.id ?: 0,
            timestamp = transactionEntityWithCategory.timestamp,
            amount = transactionEntityWithCategory.amount,
            note = transactionEntityWithCategory.note,
            categoryId = transactionEntityWithCategory.categoryId,
            name = transactionEntityWithCategory.name,
            icon = transactionEntityWithCategory.icon,
            type = mapTypeToDomain(transactionEntityWithCategory.type)
        )

        private fun mapTransactionToDomain(transactionEntity: TransactionEntity): Transaction =
        Transaction(
            id = transactionEntity.id ?: 0,
            timestamp = transactionEntity.timestamp,
            amount = transactionEntity.amount,
            note = transactionEntity.note,
            categoryId = transactionEntity.categoryId,
        )

}
